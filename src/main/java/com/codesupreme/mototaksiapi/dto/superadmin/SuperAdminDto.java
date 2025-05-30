package com.codesupreme.mototaksiapi.dto.superadmin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SuperAdminDto {

    private Long id;
    private Boolean technicalShutdown;
    private Integer latestVersionAndroid;
    private Integer latestVersionIos;
}

