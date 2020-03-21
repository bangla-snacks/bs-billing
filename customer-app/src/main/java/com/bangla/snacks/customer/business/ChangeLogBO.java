package com.bangla.snacks.customer.business;

import com.bangla.snacks.common.change.log.model.ChangeLogDB;
import com.bangla.snacks.common.change.log.repository.ChangeLogRepository;
import com.bangla.snacks.common.util.ChangeLogBuilder;
import com.bangla.snacks.customer.annotation.FixedValue;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.bangla.snacks.common.util.CommonUtil.isUpdatable;

@Component
@AllArgsConstructor
public class ChangeLogBO {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeLogBO.class);

    private ChangeLogRepository changeLogRepository;

    public ChangeLogExecutor prepareLog(Object savedObject, Object changeRequestObject, String user) {
        if (savedObject == null || changeRequestObject == null ||
                savedObject.getClass() != changeRequestObject.getClass()) {
            LOG.error("Incompatible objects to log");
            return null;
        }

        ChangeLogDB logObject = createUpdateLog(savedObject, changeRequestObject, user);
        LOG.info("Prepared log object - {}", logObject);

        return new ChangeLogExecutor(logObject);
    }

    public ChangeLogExecutor prepareLog(Object newObject, String user) {
        if (newObject == null) {
            LOG.error("Incompatible object to log");
            return new ChangeLogExecutor(null);
        }
        return new ChangeLogExecutor(ChangeLogBuilder.newBuilder(newObject, user).create(newObject));
    }

    private ChangeLogDB createUpdateLog(Object savedObject, Object changeRequestObject, String user) {
        List<Field> allFields = FieldUtils.getAllFieldsList(savedObject.getClass());
        List<Field> fixedValueFields = FieldUtils.getFieldsListWithAnnotation(savedObject.getClass(), FixedValue.class);

        List<Field> privateStrings = allFields.stream().filter(field ->
                !Modifier.isStatic(field.getModifiers())
                        && field.getType().equals(String.class)
                        && !fixedValueFields.contains(field))
                .collect(Collectors.toList());

        ChangeLogBuilder builder = ChangeLogBuilder.newBuilder(savedObject, user);


        try {
            for (Field savedField : privateStrings) {
                String fieldName = savedField.getName();

                Object savedFieldValue = FieldUtils.readDeclaredField(savedObject, fieldName, true);
                Object changeRequestFieldValue = FieldUtils.readDeclaredField(changeRequestObject, fieldName, true);

                LOG.info("Field name - {}, Old value - {}, New value - {}",
                        fieldName, savedFieldValue, changeRequestFieldValue);

                if (isUpdatable(savedFieldValue, changeRequestFieldValue)) {
                    builder.withChange(fieldName, Objects.toString(savedFieldValue),
                            Objects.toString(changeRequestFieldValue));
                }
            }
        } catch (IllegalAccessException e) {
            LOG.error("IllegalAccessException occurred - ", e);
        } catch (Exception e) {
            LOG.error("IllegalAccessException occurred - ", e);
        }

        return builder.done();
    }

    class ChangeLogExecutor {
        private ChangeLogDB toSave;

        ChangeLogExecutor(ChangeLogDB toSave) {
            this.toSave = toSave;
        }

        void execute() {
            if (toSave != null)
                changeLogRepository.save(toSave);
        }

    }
}
