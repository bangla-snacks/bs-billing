package com.bangla.snacks.customer.business;

import com.bangla.snacks.customer.db.models.AddressDB;
import com.bangla.snacks.customer.db.models.CustomerDB;
import com.bangla.snacks.customer.exception.NoSuchAddressException;
import com.bangla.snacks.customer.json.models.AddressJson;
import com.bangla.snacks.customer.repository.AddressRepository;
import com.bangla.snacks.customer.util.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AddressBO {
    private AddressRepository addressRepository;

    public AddressJson addAddress(AddressJson address, String customerId) {
        AddressDB addressDB = Transformer.toAddressDB(address);
        addressDB.setCustomer(CustomerDB.builder().customerId(customerId).build());

        try {
            AddressDB savedAddress = addressRepository.save(addressDB);
            return Transformer.toAddressJson(savedAddress);
        } catch (DataIntegrityViolationException e) {
            BOHelper.verifyDataIntegrityViolation(e, address);
        }
        return AddressJson.builder().build();
    }

    public List<AddressJson> getAllAddressOfCustomer(String customerId) {
        List<AddressDB> allAddresses = addressRepository.getAllByCustomer(CustomerDB.builder().customerId(customerId).build());
        return allAddresses.stream().map(Transformer::toAddressJson).collect(Collectors.toList());
    }

    public AddressJson getAddressById(String customerId, String addressId) {
        Optional<AddressDB> address = addressRepository.getByAddressIdAndCustomer(addressId, CustomerDB.builder().customerId(customerId).build());
        return Transformer.toAddressJson(address.orElseThrow(NoSuchAddressException::new));
    }

    public void deleteAddressById(String customerId, String addressId) {
        addressRepository.deleteAddressDBByAddressIdAndCustomer(addressId, CustomerDB.builder().customerId(customerId).build());
    }

    public AddressJson updateAddressById(String customerId, String addressId, AddressJson addressJson) {
        AddressDB currentObject = Transformer.toAddressDB(addressJson);

        Optional<AddressDB> searchResult = addressRepository.getByAddressIdAndCustomer(addressId, CustomerDB.builder().customerId(customerId).build());
        AddressDB savedObject = searchResult.orElseThrow(NoSuchAddressException::new);

        prepareUpdateObject(currentObject, savedObject);

        AddressDB updatedAddress = addressRepository.save(savedObject);

        return Transformer.toAddressJson(updatedAddress);
    }

    private void prepareUpdateObject(AddressDB currentObject, AddressDB savedObject) {
        if (currentObject.getAddressLine1() != null) {
            savedObject.setAddressLine1(currentObject.getAddressLine1());
        }
        if (currentObject.getAddressLine2() != null) {
            savedObject.setAddressLine2(currentObject.getAddressLine2());
        }
        if (currentObject.getAddressType() != null) {
            savedObject.setAddressType(currentObject.getAddressType());
        }
        if (currentObject.getArea() != null) {
            savedObject.setArea(currentObject.getArea());
        }
        if (currentObject.getPin() != null) {
            savedObject.setPin(currentObject.getPin());
        }
        if (currentObject.getLandmark() != null) {
            savedObject.setLandmark(currentObject.getLandmark());
        }
    }
}
