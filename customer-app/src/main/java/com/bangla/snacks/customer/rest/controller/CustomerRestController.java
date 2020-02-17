package com.bangla.snacks.customer.rest.controller;

import com.bangla.snacks.customer.business.CustomerBO;
import com.bangla.snacks.customer.exception.NotImplementedException;
import com.bangla.snacks.customer.json.models.CustomerJson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/personal")
public class CustomerRestController {
    private CustomerBO customerBO;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerJson customer) {
        customer.setCreateDate(new Date());
        customer.setAddresses(Collections.emptyList()); // Address should be added later
        return new ResponseEntity<>(customerBO.addCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCustomers() {
        return new ResponseEntity<>(customerBO.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCustomer(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(customerBO.getCustomerById(customerId), HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerJson customer) {
        throw new NotImplementedException();
    }

    @DeleteMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteCustomer(@PathVariable("customerId") String customerId) {
        throw new NotImplementedException();
    }
}
