package com.bangla.snacks.customer.json.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class CustomerPhone implements Serializable {
    private static final long serialVersionUID = -5541257885214569L;

    private String customerPhoneId;
    private String phoneNumber;
    private String verifiedFlag;
    private String phoneNumberType;
}
