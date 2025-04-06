package com.codesupreme.mototaksiapi.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NotificationDto {

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
