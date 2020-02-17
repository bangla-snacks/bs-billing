package com.bangla.snacks.customer.constants;

import lombok.AllArgsConstructor;

public interface DBConstants {
    String TABLE_CUSTOMER_DB = "BS_TBL_CUSTOMER";
    String TABLE_ADDRESS_DB = "BS_TBL_ADDRESS";

    String COL_CUSTOMER_DB_ID = "CUST_ID";
    String COL_CUSTOMER_DB_FIRST_NAME = "FIRST_NAME";
    String COL_CUSTOMER_DB_LAST_NAME = "LAST_NAME";
    String COL_CUSTOMER_DB_EMAIL = "CUST_EMAIL";
    String COL_CUSTOMER_DB_CONTACT = "CUST_CONTACT";
    String COL_CUSTOMER_DB_DT_CREATED = "ADDR_CREATE_DATE";

    String COL_ADDRESS_DB_ID = "ADDR_ID";
    String COL_ADDRESS_DB_ADDR_LN_1 = "ADDR_LN_1";
    String COL_ADDRESS_DB_ADDR_LN_2 = "ADDR_LN_2";
    String COL_ADDRESS_DB_AREA = "ADDR_AREA";
    String COL_ADDRESS_DB_PIN = "ADDR_PIN";
    String COL_ADDRESS_DB_LANDMARK = "ADDR_LNDMRK";
    String COL_ADDRESS_DB_TYP = "ADDR_TYPE";
    String COL_ADDRESS_DB_DT_CREATED = "ADDR_CREATE_DATE";

    String MAPPED_BY_CUSTOMER = "customer";

    String UNIQUE_CUSTOMER_ID_ADDR_TYPE             = "UNIQUE_CUSTOMER_ID_ADDR_TYPE";
    String UNIQUE_CUSTOMER_CONTACT_NO               = "UNIQUE_CUSTOMER_CONTACT_NO";
    String UNIQUE_CUSTOMER_EMAIL                    = "UNIQUE_CUSTOMER_EMAIL";
    String FOREIGN_KEY_CUSTOMER_ID_ADDRESS_TABLE    = "TBL_CUSTOMER_CUSTOMER_ID_TBL_ADDRESS";

    String CV_MESSAGE_PROP_UNIQUE_CUSTOMER_ID_ADDR_TYPE = "application.error-messages.duplicate-address-type";
    String CV_MESSAGE_PROP_UNIQUE_CUSTOMER_CONTACT_NO = "application.error-messages.duplicate-phone";
    String CV_MESSAGE_PROP_UNIQUE_CUSTOMER_EMAIL = "application.error-messages.duplicate-email";
    String CV_MESSAGE_PROP_FOREIGN_KEY_CUSTOMER_ID_ADDRESS_TABLE = "";

    @AllArgsConstructor
    enum ApplicationConstraints {
        UNIQUE_CUSTOMER_ID_ADDR_TYPE(DBConstants.UNIQUE_CUSTOMER_ID_ADDR_TYPE, CV_MESSAGE_PROP_UNIQUE_CUSTOMER_ID_ADDR_TYPE),
        UNIQUE_CUSTOMER_CONTACT_NO(DBConstants.UNIQUE_CUSTOMER_CONTACT_NO, CV_MESSAGE_PROP_UNIQUE_CUSTOMER_CONTACT_NO),
        UNIQUE_CUSTOMER_EMAIL(DBConstants.UNIQUE_CUSTOMER_EMAIL, CV_MESSAGE_PROP_UNIQUE_CUSTOMER_EMAIL),
        FOREIGN_KEY_CUSTOMER_ID_ADDRESS_TABLE(DBConstants.FOREIGN_KEY_CUSTOMER_ID_ADDRESS_TABLE, CV_MESSAGE_PROP_FOREIGN_KEY_CUSTOMER_ID_ADDRESS_TABLE);

        private String constraintName;
        private String messagePropertyName;

        public static ApplicationConstraints byConstraintName(String constraintName) {
            for(ApplicationConstraints constraint : ApplicationConstraints.values()) {
                if(constraintName.equals(constraint.constraintName)) {
                    return constraint;
                }
            }
            return null;
        }

        public String getMessagePropertyName() {
            return this.messagePropertyName;
        }
    }
}
