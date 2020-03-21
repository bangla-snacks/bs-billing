package com.bangla.snacks.common.util;

import com.bangla.snacks.common.change.log.model.ChangeLogDB;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChangeLogBuilder {
    private final String table;
    private final ChangeLogDB changeLog;
    private final JSONObject json;

    private ChangeLogBuilder(Object obj, String changeBy) {
        this.json = new JSONObject();
        this.changeLog = new ChangeLogDB();
        this.changeLog.setChangeBy(changeBy);
        this.table = getTableName(obj);
        this.changeLog.setChangeDate(CommonUtil.getCurrentDateAsString());

        json.put("table", table);
    }

    public static ChangeLogBuilder newBuilder(Object obj, String changeBy) {
        return new ChangeLogBuilder(obj, changeBy);
    }

    public ChangeLogDB create(Object record) {
        String desc = String.format("Created new '%s' with data : %s",
                record.getClass().getSimpleName().replace("DB", ""), record.toString());
        json.put("changeSet", desc);

        this.changeLog.setChangeDescription(json.toString());

        return this.changeLog;
    }

    public ChangeLogBuilder withChange(String field, String oldValue, String newValue) {
        changeSet(field, oldValue, newValue);
        return this;
    }
    public ChangeLogDB done() {
        this.changeLog.setChangeDescription(json.toString());
        return this.changeLog;
    }

    private String getTableName(Object obj) {
        String className = obj.getClass().getSimpleName();
        if ("CustomerDB".equals(className)) {
            return "Customer";
        }
        if ("AddressDB".equals(className)) {
            return "Address";
        }
        return "";
    }

    private void changeSet(String fieldName, String oldValue, String newValue) {
        if(!json.has("changeSet")) {
            json.put("changeSet", new JSONArray());
        }
        JSONArray changeSet = json.getJSONArray("changeSet");
        changeSet.put(String.format("Changed value of '%s' from '%s' to '%s'", fieldName, oldValue, newValue));
    }
}
