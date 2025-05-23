package com.codesupreme.mototaksiapi.dto.courier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CourierDto {

    private Long id;

    private String name;
    private String surname;
    private String motoImage;
    private String motoName;
    private String motoVolume;
    private List<String> currentLocation;
    private String phoneNumber;
    private String password;
    private Double balance;
    private Boolean online;
    private Boolean currentlyDelivering;
    @JsonProperty("isDisable")
    private Boolean isDisable;
    private Date createdAt;
}
