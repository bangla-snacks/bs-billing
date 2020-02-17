package com.bangla.snacks.customer.rest.controller;

import com.bangla.snacks.customer.business.AddressBO;
import com.bangla.snacks.customer.json.models.AddressJson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressRestController {
    private AddressBO addressBO;

    @PostMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addAddress(@RequestBody AddressJson address,
                                                  @PathVariable("customerId") String customerId) {
        address.setCreateDate(new Date());
        return new ResponseEntity<>(addressBO.addAddress(address, customerId), HttpStatus.CREATED);
    }
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllAddresses(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(addressBO.getAllAddressOfCustomer(customerId), HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAddress(@RequestBody AddressJson address,
                                             @PathVariable("customerId") String customerId,
                                                @PathVariable("addressId") String addressId) {
        return new ResponseEntity<>(addressBO.updateAddressById(customerId, addressId, address), HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAddress(@PathVariable("customerId") String customerId,
                                                @PathVariable("addressId") String addressId) {
        return new ResponseEntity<>(addressBO.getAddressById(customerId, addressId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteAddress(@PathVariable("customerId") String customerId,
                                             @PathVariable("addressId") String addressId) {
        addressBO.deleteAddressById(customerId, addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
