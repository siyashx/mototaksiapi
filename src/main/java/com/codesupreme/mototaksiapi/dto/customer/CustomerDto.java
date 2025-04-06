package com.codesupreme.mototaksiapi.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerDto {

    private Long id;

    private String name;
    private String surname;
    private String gender;
    private Integer weight;
    private String phoneNumber;
    private String password;
    @JsonProperty("isDisable")
    private Boolean isDisable;
    private Date createdAt;
}
