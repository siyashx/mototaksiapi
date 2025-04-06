package com.codesupreme.mototaksiapi.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long courierId;
    @ElementCollection
    private List<String> fromAddress;

    @ElementCollection
    private List<String> toAddress;

    @ElementCollection
    private List<Long> cancelledCourierIds;

    private String status;
    private Double price;
    private Double distance;
    @JsonProperty("isDisable")
    private Boolean isDisable;
    private Date createdAt;
}
