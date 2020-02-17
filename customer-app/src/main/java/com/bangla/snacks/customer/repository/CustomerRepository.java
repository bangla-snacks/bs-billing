package com.bangla.snacks.customer.repository;

import com.bangla.snacks.customer.db.models.CustomerDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDB, String> {
}
