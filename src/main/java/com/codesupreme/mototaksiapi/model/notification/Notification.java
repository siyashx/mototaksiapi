package com.codesupreme.mototaksiapi.model.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;
    private String courierId;
    private String customerId;
    private String message;
    private Boolean isRead;
    private String type;
    private Boolean isDeleted;
    private Date createdAt;
}
