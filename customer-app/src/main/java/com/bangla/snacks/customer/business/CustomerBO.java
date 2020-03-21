package com.bangla.snacks.customer.business;

import com.bangla.snacks.common.exception.NoSuchCustomerException;
import com.bangla.snacks.customer.db.models.CustomerDB;
import com.bangla.snacks.customer.json.models.CustomerJson;
import com.bangla.snacks.customer.repository.CustomerRepository;
import com.bangla.snacks.customer.util.Transformer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.bangla.snacks.common.util.CommonUtil.isUpdatable;
import static com.bangla.snacks.common.util.CommonUtil.titleCase;

@AllArgsConstructor
@Component
@Transactional
public class CustomerBO {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerBO.class);

    private CustomerRepository customerRepository;
    private ChangeLogBO changeLogBO;

    public CustomerJson addCustomer(CustomerJson customer) {
        try {
            ChangeLogBO.ChangeLogExecutor executor = changeLogBO.prepareLog(customer, customer.getUserId());
            CustomerDB savedCustomer = customerRepository.save(Transformer.toCustomerDB(customer));

            executor.execute();
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

    public CustomerJson getCustomerByUserId(@NotNull String userId) {
        return Transformer.toCustomerJson(findByUserId(userId));
    }

    public CustomerJson updateCustomer(@NotNull String userId, CustomerJson toCustomer) {
        CustomerDB foundCustomer = findByUserId(userId);
        return Transformer.toCustomerJson(doUpdateCustomer(foundCustomer, Transformer.toCustomerDB(toCustomer)));
    }

    public void deleteCustomer(@NotNull String userId) {
        CustomerDB foundCustomer = findByUserId(userId);
        customerRepository.deleteById(foundCustomer.getCustomerId());
    }

    private CustomerDB doUpdateCustomer(CustomerDB savedCustomer, CustomerDB changeRequest) {
        ChangeLogBO.ChangeLogExecutor executor = changeLogBO.prepareLog(savedCustomer, changeRequest, savedCustomer.getUserId());

        prepareCustomerForUpdate(savedCustomer, changeRequest);

        CustomerDB updatedCustomer = customerRepository.save(savedCustomer);

        executor.execute();

        return updatedCustomer;
    }

    CustomerDB findByUserId(String userId) {
        return customerRepository.findByUserId(userId).orElseThrow(NoSuchCustomerException::new);
    }

    private void prepareCustomerForUpdate(CustomerDB savedCustomer, CustomerDB changeRequest) {
        if (isUpdatable(changeRequest.getFirstName(), savedCustomer.getFirstName())) {
            String changeRequestFirstName = titleCase(changeRequest.getFirstName());
            savedCustomer.setFirstName(changeRequestFirstName);
        }

        if (isUpdatable(changeRequest.getLastName(), savedCustomer.getLastName())) {
            String changeRequestLastName = titleCase(changeRequest.getLastName());
            savedCustomer.setLastName(changeRequestLastName);
        }

        if (isUpdatable(changeRequest.getContactNo(), savedCustomer.getContactNo())) {
            savedCustomer.setContactNo(changeRequest.getContactNo());
        }

        if (isUpdatable(changeRequest.getEmail(), savedCustomer.getEmail())) {
            savedCustomer.setEmail(changeRequest.getEmail());
        }
    }
}

