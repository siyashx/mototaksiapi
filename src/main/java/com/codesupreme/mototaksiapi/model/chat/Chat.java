package com.codesupreme.mototaksiapi.model.chat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courierId;
    private String customerId;
    @ElementCollection
    private List<Long> isReadIds;
    private String message;
    private String timestamp;
}

