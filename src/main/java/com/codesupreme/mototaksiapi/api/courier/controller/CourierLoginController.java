package com.codesupreme.mototaksiapi.api.courier.controller;

import com.codesupreme.mototaksiapi.dto.courier.CourierDto;
import com.codesupreme.mototaksiapi.model.LoginRequest;
import com.codesupreme.mototaksiapi.service.impl.courier.CourierServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v7/login/courier")
public class CourierLoginController {

    private final CourierServiceImpl courierServiceImpl;

    public CourierLoginController(CourierServiceImpl courierServiceImpl) {
        this.courierServiceImpl = courierServiceImpl;
    }

    // Login
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String phoneNumber = loginRequest.getPhoneNumber();
        String password = loginRequest.getPassword();

        CourierDto courierDto = courierServiceImpl.findCourierByPhoneNumber(phoneNumber);

        if (courierDto != null && courierDto.getPassword().equals(password)) {
            if (!courierDto.getIsDisable()) {
                return ResponseEntity.ok(courierDto);
            } else {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("This courier is inactive or deleted");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                "Invalid couriername, email or password");
    }
}
