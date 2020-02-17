package com.bangla.snacks.customer.business;

import com.bangla.snacks.customer.constants.DBConstants;
import com.bangla.snacks.customer.exception.AppConstraintViolationException;
import com.bangla.snacks.customer.json.models.AddressJson;
import com.bangla.snacks.customer.json.models.CustomerJson;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

public class BOHelper {
    private static final Logger LOG = LoggerFactory.getLogger(BOHelper.class);
    private BOHelper() {}

    public static void verifyDataIntegrityViolation(DataIntegrityViolationException e, Object obj) {
        Throwable cause = e.getCause();
        if(cause instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) cause;
            String constraintName = cve.getConstraintName();
            LOG.info("Constraint Name - {}", constraintName);
            switch (DBConstants.ApplicationConstraints.byConstraintName(constraintName)) {
                case UNIQUE_CUSTOMER_ID_ADDR_TYPE:
                    throw new AppConstraintViolationException(cve, ((AddressJson) obj).getAddressType());
                case UNIQUE_CUSTOMER_CONTACT_NO:
                    throw new AppConstraintViolationException(cve, ((CustomerJson) obj).getContactNo());
                case UNIQUE_CUSTOMER_EMAIL:
                    throw new AppConstraintViolationException(cve, ((CustomerJson) obj).getEmail());
            }
        }
    }
}
