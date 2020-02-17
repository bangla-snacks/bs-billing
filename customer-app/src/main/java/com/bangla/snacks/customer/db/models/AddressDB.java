package com.bangla.snacks.customer.db.models;

import com.bangla.snacks.customer.constants.DBConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = DBConstants.TABLE_ADDRESS_DB)
@Table(
        uniqueConstraints = @UniqueConstraint(
                name=DBConstants.UNIQUE_CUSTOMER_ID_ADDR_TYPE,
                columnNames = {
                        DBConstants.COL_CUSTOMER_DB_ID,
                        DBConstants.COL_ADDRESS_DB_TYP
                })
)
public class AddressDB implements Serializable, DBConstants {
    private static final long serialVersionUID = -92194927129323L;

    @Id
    @Column(name = COL_ADDRESS_DB_ID, length = 20)
    private String addressId;

    @Column(name = COL_ADDRESS_DB_ADDR_LN_1, length = 40, nullable = false)
    private String addressLine1;

    @Column(name = COL_ADDRESS_DB_ADDR_LN_2, length = 40)
    private String addressLine2;

    @Column(name = COL_ADDRESS_DB_AREA, length = 40, nullable = false)
    private String area;

    @Column(name = COL_ADDRESS_DB_LANDMARK, length = 40, nullable = false)
    private String landmark;

    @Column(name = COL_ADDRESS_DB_PIN, length = 40, nullable = false)
    private String pin;

    @Column(name = COL_ADDRESS_DB_TYP, length = 40)
    private String addressType;

    @Column(name = COL_ADDRESS_DB_DT_CREATED, updatable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = COL_CUSTOMER_DB_ID, foreignKey = @ForeignKey(name = FOREIGN_KEY_CUSTOMER_ID_ADDRESS_TABLE))
    private CustomerDB customer;
}
