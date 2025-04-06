package com.codesupreme.mototaksiapi.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChatDto {

    private Long id;
    private Long courierId;
    private String customerId;
    private List<Long> isReadIds;
    private String message;
    private String timestamp;
}
