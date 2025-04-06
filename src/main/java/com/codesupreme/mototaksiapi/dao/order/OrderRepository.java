package com.codesupreme.mototaksiapi.dao.order;

import com.codesupreme.mototaksiapi.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}