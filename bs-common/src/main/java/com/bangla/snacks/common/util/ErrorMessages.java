package com.bangla.snacks.common.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ErrorMessages {
    @Value("${application.error-messages.duplicate-email}")
    private String duplicateEmailErrorMessage;
    @Value("${application.error-messages.duplicate-phone}")
    private String duplicatePhoneErrorMessage;
    @Value("${application.error-messages.invalid-first-name}")
    private String invalidFirstNameErrorMessage;
    @Value("${application.error-messages.invalid-last-name}")
    private String invalidLastNameErrorMessage;
    @Value("${application.error-messages.duplicate-address-type}")
    private String duplicateAddressTypeForCustomerErrorMessage;

}
