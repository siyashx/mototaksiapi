package com.codesupreme.mototaksiapi.model.courier;

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
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String motoImage;
    private String motoName;
    private String motoVolume;
    @ElementCollection
    private List<String> currentLocation;
    private String phoneNumber;
    private String password;
    private Boolean online;
    private Boolean currentlyDelivering;
    @JsonProperty("isDisable")
    private Boolean isDisable;
    private Date createdAt;
}
