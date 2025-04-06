package com.codesupreme.mototaksiapi.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdminDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String password;
}
