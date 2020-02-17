package com.bangla.snacks.customer.json.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CustomerJson {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;
    private List<AddressJson> addresses;
}
