package com.bangla.snacks.customer.rest.controller;

import com.bangla.snacks.customer.business.CustomerBO;
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

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCustomer(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(customerBO.getCustomerByUserId(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCustomer(@PathVariable("userId") String userId, @RequestBody CustomerJson customer) {
        return new ResponseEntity<>(customerBO.updateCustomer(userId, customer), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteCustomer(@PathVariable("userId") String userId) {
        customerBO.deleteCustomer(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
