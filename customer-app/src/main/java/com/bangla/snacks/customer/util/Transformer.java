package com.bangla.snacks.customer.util;

import com.bangla.snacks.common.util.CommonUtil;
import com.bangla.snacks.customer.db.models.AddressDB;
import com.bangla.snacks.customer.db.models.CustomerDB;
import com.bangla.snacks.customer.json.models.AddressJson;
import com.bangla.snacks.customer.json.models.CustomerJson;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bangla.snacks.common.util.CommonUtil.isUpdatable;
import static com.bangla.snacks.common.util.CommonUtil.titleCase;

public final class Transformer {
    private Transformer() {}

    public static CustomerJson toCustomerJson(CustomerDB customerDB) {
        return CustomerJson.builder()
                .customerId(customerDB.getCustomerId())
                .firstName(customerDB.getFirstName())
                .lastName(customerDB.getLastName())
                .email(customerDB.getEmail())
                .contactNo(customerDB.getContactNo())
                .createDate(customerDB.getCreateDate())
                .userId(customerDB.getUserId())
                .addresses(customerDB.getAddresses()
                        .stream()
                        .map(Transformer::toAddressJson)
                        .collect(Collectors.toList()))
                .build();
    }

    public static CustomerDB toCustomerDB(CustomerJson customerJson) {
        CustomerDB customerDb = CustomerDB.builder()
                .customerId(String.format("CUST%s", CommonUtil.generateId()))
                .firstName(titleCase(customerJson.getFirstName()))
                .lastName(titleCase(customerJson.getLastName()))
                .email(customerJson.getEmail())
                .contactNo(customerJson.getContactNo())
                .createDate(customerJson.getCreateDate())
                .userId(customerJson.getUserId())
                .addresses(Optional.ofNullable(customerJson.getAddresses())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(Transformer::toAddressDB)
                        .collect(Collectors.toList()))
                .build();
        customerDb.getAddresses().forEach(addressDB -> addressDB.setCustomer(customerDb));
        return customerDb;
    }

    public static AddressJson toAddressJson(AddressDB addressDB) {
        return AddressJson.builder()
                .addressId(addressDB.getAddressId())
                .addressLine1(addressDB.getAddressLine1())
                .addressLine2(addressDB.getAddressLine2())
                .area(addressDB.getArea())
                .landmark(addressDB.getLandmark())
                .pin(addressDB.getPin())
                .createDate(addressDB.getCreateDate())
                .addressType(addressDB.getAddressType())
                .build();
    }

    public static AddressDB toAddressDB(AddressJson addressJson) {
        return AddressDB.builder()
                .addressId(String.format("ADDR%s", CommonUtil.generateId()))
                .addressLine1(addressJson.getAddressLine1())
                .addressLine2(addressJson.getAddressLine2())
                .area(addressJson.getArea())
                .landmark(addressJson.getLandmark())
                .pin(addressJson.getPin())
                .createDate(addressJson.getCreateDate())
                .addressType(addressJson.getAddressType())
                .build();
    }

}
