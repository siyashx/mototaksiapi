package com.codesupreme.mototaksiapi.api.courier.controller;

import com.codesupreme.mototaksiapi.dto.courier.CourierDto;
import com.codesupreme.mototaksiapi.service.impl.courier.CourierServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v7/courier")
public class CourierController {

    private final CourierServiceImpl courierServiceImpl;

    public CourierController(CourierServiceImpl courierServiceImpl) {
        this.courierServiceImpl = courierServiceImpl;
    }

    // List
    @GetMapping
    public List<CourierDto> getAllCouriers() {
        return courierServiceImpl.getAllCouriers();
    }

    // Id
    @GetMapping("/{courierId}")
    public ResponseEntity<?> getCourierById(@PathVariable("courierId") Long courierId) {
        CourierDto courierDto = courierServiceImpl.getCourierById(courierId);
        if (courierDto != null) {
            return ResponseEntity.ok(courierDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Courier doesn't exist with given id..");
    }

    // Update
    @PutMapping("/{courierId}")
    public ResponseEntity<?> updateCourier(
            @PathVariable("courierId") Long id,
            @RequestBody CourierDto courierDto
    ) {
        CourierDto updatedCourier = courierServiceImpl.updateCourier(id, courierDto);
        if (updatedCourier != null) {
            return ResponseEntity.ok(updatedCourier);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete
    @DeleteMapping("/{courierId}")
    public ResponseEntity<?> deleteCourierById(@PathVariable("courierId") Long courierId) {
        courierServiceImpl.deleteCourierById(courierId);
        return ResponseEntity.ok().build();
    }

    // Create
    @PostMapping
    public ResponseEntity<CourierDto> createCourier(@RequestBody CourierDto courierDto) {
        return courierServiceImpl.createCourier(courierDto);
    }
}
