package com.bangla.snacks.customer.business;

import com.bangla.snacks.common.exception.NoSuchAddressException;
import com.bangla.snacks.customer.db.models.AddressDB;
import com.bangla.snacks.customer.db.models.CustomerDB;
import com.bangla.snacks.customer.json.models.AddressJson;
import com.bangla.snacks.customer.repository.AddressRepository;
import com.bangla.snacks.customer.repository.CustomerRepository;
import com.bangla.snacks.customer.util.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bangla.snacks.common.util.CommonUtil.isUpdatable;
import static com.bangla.snacks.common.util.CommonUtil.titleCase;

@Component
@AllArgsConstructor
public class AddressBO {
    private AddressRepository addressRepository;
    private CustomerBO customerBO;
    private ChangeLogBO changeLogBO;

    public AddressJson addAddress(AddressJson address, String userId) {
        AddressDB addressDB = Transformer.toAddressDB(address);
        CustomerDB customer = customerBO.findByUserId(userId);
        addressDB.setCustomer(customer);

        try {
            ChangeLogBO.ChangeLogExecutor executor = changeLogBO.prepareLog(addressDB, userId);
            AddressDB savedAddress = addressRepository.save(addressDB);
            executor.execute();
            return Transformer.toAddressJson(savedAddress);
        } catch (DataIntegrityViolationException e) {
            BOHelper.verifyDataIntegrityViolation(e, address);
        }
        return AddressJson.builder().build();
    }

    public List<AddressJson> getAllAddressOfCustomer(String userId) {
        List<AddressDB> allAddresses = addressRepository.getAllByCustomer(customerBO.findByUserId(userId));
        return allAddresses.stream().map(Transformer::toAddressJson).collect(Collectors.toList());
    }

    public AddressJson getAddressByType(String userId, String addressType) {
        return Transformer.toAddressJson(findAddressByTypeAndUserId(addressType, userId));
    }

    public void deleteAddress(String userId, String addressType) {
        addressRepository.deleteAddressDBByAddressTypeAndCustomer(addressType, customerBO.findByUserId(userId));
    }

    public AddressJson updateAddressById(String userId, String addressType, AddressJson addressJson) {
        AddressDB currentObject = Transformer.toAddressDB(addressJson);

        AddressDB savedObject = findAddressByTypeAndUserId(addressType, userId);

        ChangeLogBO.ChangeLogExecutor executor = changeLogBO.prepareLog(savedObject, currentObject, userId);

        prepareUpdateObject(currentObject, savedObject);

        AddressDB updatedAddress = addressRepository.save(savedObject);
        executor.execute();
        return Transformer.toAddressJson(updatedAddress);
    }

    private AddressDB findAddressByTypeAndUserId(String addressType, String userId) {
        Optional<AddressDB> searchResult = addressRepository.getByAddressTypeAndCustomer(addressType, customerBO.findByUserId(userId));
        return searchResult.orElseThrow(NoSuchAddressException::new);
    }

    private void prepareUpdateObject(AddressDB currentObject, AddressDB savedObject) {
        if (isUpdatable(currentObject.getAddressLine1(), savedObject.getAddressLine1())) {
            String changeRequestAddressLine1 = titleCase(currentObject.getAddressLine1());
            savedObject.setAddressLine1(changeRequestAddressLine1);
        }
        if (currentObject.getAddressLine2() != null) {
            String changeRequestAddressLine2 = titleCase(currentObject.getAddressLine2());
            savedObject.setAddressLine2(changeRequestAddressLine2);
        }
        if (isUpdatable(currentObject.getAddressType(), savedObject.getAddressType())) {
            String changeRequestAddressType = titleCase(currentObject.getAddressType());
            savedObject.setAddressType(changeRequestAddressType);
        }
        if (isUpdatable(currentObject.getArea(), savedObject.getArea())) {
            String changeRequestArea = titleCase(currentObject.getArea());
            savedObject.setArea(changeRequestArea);
        }
        if (currentObject.getPin() != null) {
            savedObject.setPin(currentObject.getPin());
        }
        if (currentObject.getLandmark() != null) {
            String changeRequestArea = titleCase(currentObject.getLandmark());
            savedObject.setLandmark(changeRequestArea);
        }
    }
}
