package com.bangla.snacks.customer.business;

import com.bangla.snacks.customer.constants.DBConstants.ApplicationConstraints;
import com.bangla.snacks.customer.db.models.CustomerDB;
import com.bangla.snacks.customer.exception.AppConstraintViolationException;
import com.bangla.snacks.customer.exception.NoSuchCustomerException;
import com.bangla.snacks.customer.json.models.AddressJson;
import com.bangla.snacks.customer.json.models.CustomerJson;
import com.bangla.snacks.customer.repository.CustomerRepository;
import com.bangla.snacks.customer.util.Transformer;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CustomerBO {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerBO.class);

    private CustomerRepository customerRepository;

    public CustomerJson addCustomer(CustomerJson customer) {
        try {
            List<AddressJson> addresses = customer.getAddresses();
            if (!CollectionUtils.isEmpty(addresses) && addresses.size() > 1) {
                LOG.info("Address size before check - {}", addresses.size());
                LOG.warn("More than 1 address were provided for saving. Only the first address will be saved.");
                addresses.subList(1, addresses.size()).clear();
                LOG.info("Address size after check - {}", addresses.size());
            }
            CustomerDB savedCustomer = customerRepository.save(Transformer.toCustomerDB(customer));
            return Transformer.toCustomerJson(savedCustomer);
        } catch (DataIntegrityViolationException e) {
            BOHelper.verifyDataIntegrityViolation(e, customer);
        }
        return CustomerJson.builder().build();
    }

    public List<CustomerJson> getAllCustomers() {
        return customerRepository.findAll().stream().map(Transformer::toCustomerJson).collect(Collectors.toList());
    }

    public CustomerJson getCustomerById(@NotNull String customerId) {
        return Transformer.toCustomerJson(customerRepository.findById(customerId).orElseThrow(NoSuchCustomerException::new));
    }
    public CustomerJson updateCustomerById(@NotNull String customerId, CustomerDB customer) {
        return Transformer.toCustomerJson(customerRepository.findById(customerId).orElseThrow(NoSuchCustomerException::new));
    }
    @Transactional
    public void deleteCustomer(@NotNull String customerId) {
        customerRepository.deleteById(customerId);
    }
}

