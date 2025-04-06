package com.codesupreme.mototaksiapi.api.customer.controller;

import com.codesupreme.mototaksiapi.dto.customer.CustomerDto;
import com.codesupreme.mototaksiapi.model.LoginRequest;
import com.codesupreme.mototaksiapi.service.impl.customer.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v7/login/customer")
public class CustomerLoginController {

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerLoginController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    // Login
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String phoneNumber = loginRequest.getPhoneNumber();
        String password = loginRequest.getPassword();

        CustomerDto customerDto = customerServiceImpl.findCustomerByPhoneNumber(phoneNumber);

        if (customerDto != null && customerDto.getPassword().equals(password)) {
            if (!customerDto.getIsDisable()) {
                return ResponseEntity.ok(customerDto);
            } else {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("This customer is inactive or deleted");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                "Invalid customername, email or password");
    }
}
