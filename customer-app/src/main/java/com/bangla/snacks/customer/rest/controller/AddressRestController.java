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

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addAddress(@RequestBody AddressJson address,
                                                  @PathVariable("userId") String userId) {
        address.setCreateDate(new Date());
        return new ResponseEntity<>(addressBO.addAddress(address, userId), HttpStatus.CREATED);
    }
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllAddresses(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(addressBO.getAllAddressOfCustomer(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}/{addressType}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAddress(@RequestBody AddressJson address,
                                             @PathVariable("userId") String userId,
                                                @PathVariable("addressType") String addressType) {
        return new ResponseEntity<>(addressBO.updateAddressById(userId, addressType, address), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/{addressType}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAddress(@PathVariable("userId") String userId,
                                                @PathVariable("addressType") String addressType) {
        return new ResponseEntity<>(addressBO.getAddressByType(userId, addressType), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}/{addressType}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteAddress(@PathVariable("userId") String userId,
                                             @PathVariable("addressType") String addressType) {
        addressBO.deleteAddress(userId, addressType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
