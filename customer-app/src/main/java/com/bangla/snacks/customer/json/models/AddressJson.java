package com.bangla.snacks.customer.json.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AddressJson {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String addressId;
    private String addressLine1;
    private String addressLine2;
    private String area;
    private String landmark;
    private String pin;
    private String addressType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;
}
