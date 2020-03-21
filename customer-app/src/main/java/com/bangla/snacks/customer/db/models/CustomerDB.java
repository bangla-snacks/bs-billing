package com.bangla.snacks.customer.db.models;

import com.bangla.snacks.common.constants.DBConstants;
import com.bangla.snacks.customer.annotation.FixedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = DBConstants.TABLE_CUSTOMER_DB)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = DBConstants.UNIQUE_CUSTOMER_EMAIL,
                        columnNames = {DBConstants.COL_CUSTOMER_DB_EMAIL}
                ),
                @UniqueConstraint(
                        name = DBConstants.UNIQUE_CUSTOMER_CONTACT_NO,
                        columnNames = {DBConstants.COL_CUSTOMER_DB_CONTACT}
                ),
                @UniqueConstraint(
                        name = DBConstants.UNIQUE_CUSTOMER_USER_ID,
                        columnNames = {DBConstants.COL_CUSTOMER_DB_USR_ID}
                )
        }

)
public class CustomerDB implements Serializable, DBConstants {
    private static final long serialVersionUID = -2912991238858432L;

    @Id
    @Column(name = COL_CUSTOMER_DB_ID, length = 20)
    @FixedValue
    private String customerId;

    @Column(name = COL_CUSTOMER_DB_USR_ID, length = 20, nullable = false)
    @FixedValue
    private String userId;

    @Column(name = COL_CUSTOMER_DB_FIRST_NAME, length = 20, nullable = false)
    private String firstName;

    @Column(name = COL_CUSTOMER_DB_LAST_NAME, length = 20)
    private String lastName;

    @Column(name = COL_CUSTOMER_DB_EMAIL, nullable = false, length = 50)
    private String email;

    @Column(name = COL_CUSTOMER_DB_DT_CREATED, nullable = false, updatable = false)
    @FixedValue
    private Date createDate;

    @Column(name = COL_CUSTOMER_DB_CONTACT, nullable = false, length = 20)
    private String contactNo;

    @OneToMany(mappedBy = MAPPED_BY_CUSTOMER, cascade = CascadeType.ALL)
    private List<AddressDB> addresses;

    @Override
    public String toString() {
        return new StringBuilder().append("{")
                .append(", userId:'")
                .append(userId)
                .append(", firstName:'")
                .append(firstName)
                .append(", lastName:'")
                .append(lastName)
                .append(", email:'")
                .append(email)
                .append(", createDate:")
                .append(createDate)
                .append( ", contactNo:'")
                .append(contactNo)
                .append(", addresses:")
                .append(addresses)
                .append('}')
                .toString();
    }
}
