package com.codesupreme.mototaksiapi.service.inter.courier;

import com.codesupreme.mototaksiapi.dto.courier.CourierDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourierServiceInter {

    List<CourierDto> getAllCouriers();
    CourierDto getCourierById(Long id);
    CourierDto updateCourier(Long id, CourierDto courierDto);
    ResponseEntity<CourierDto> createCourier(CourierDto courierDto);
    void deleteCourierById(Long id);
    Boolean isCourierPhoneNumberTaken(String phoneNumber);
    CourierDto findCourierByPhoneNumber(String phoneNumber);
}
