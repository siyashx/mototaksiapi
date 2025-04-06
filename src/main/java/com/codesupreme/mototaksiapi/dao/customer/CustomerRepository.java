package com.codesupreme.mototaksiapi.dao.customer;

import com.codesupreme.mototaksiapi.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByPhoneNumber(String phoneNumber);

}
