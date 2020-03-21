package com.bangla.snacks.customer.repository;

import com.bangla.snacks.customer.db.models.AddressDB;
import com.bangla.snacks.customer.db.models.CustomerDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressDB, String> {
//    @Query(value = "select address from AddressDB address where customer = 1")
    List<AddressDB> getAllByCustomer(CustomerDB customer);

//    @Query(value = "select address from AddressDB address where address.addressId = 1 and address.customer = 2")
    Optional<AddressDB> getByAddressIdAndCustomer(String addressId, CustomerDB customer);

    Optional<AddressDB> getByAddressTypeAndCustomer(String addressType, CustomerDB customer);

    @Transactional
    void deleteAddressDBByAddressIdAndCustomer(String addressId, CustomerDB customer);

    void deleteAddressDBByAddressTypeAndCustomer(String addressType, CustomerDB customer);


}
