package com.codesupreme.mototaksiapi.dao.notification;

import com.codesupreme.mototaksiapi.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
