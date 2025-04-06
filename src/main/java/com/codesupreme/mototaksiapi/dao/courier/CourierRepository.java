package com.codesupreme.mototaksiapi.dao.courier;

import com.codesupreme.mototaksiapi.model.courier.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
    Courier findCourierByPhoneNumber(String phoneNumber);

}