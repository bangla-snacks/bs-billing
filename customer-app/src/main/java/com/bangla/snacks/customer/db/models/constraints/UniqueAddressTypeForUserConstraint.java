package com.bangla.snacks.customer.db.models.constraints;

import com.bangla.snacks.customer.constants.DBConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
public class UniqueAddressTypeForUserConstraint implements Serializable, DBConstants {
    private static final long serialVersionUID = -49228913123595209L;
    @Column(name = COL_CUSTOMER_DB_ID)
    private String customerId;
    @Column(name = COL_ADDRESS_DB_TYP)
    private String addressType;

}
