package com.codesupreme.mototaksiapi.api.superadmin;

import com.codesupreme.mototaksiapi.dto.superadmin.SuperAdminDto;
import com.codesupreme.mototaksiapi.service.impl.superadmin.SuperAdminServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v7/superAdmin")
public class SuperAdminController {

    private final SuperAdminServiceImpl service;


    public SuperAdminController(SuperAdminServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SuperAdminDto>> getAllSuperAdmin() {
        List<SuperAdminDto> all = service.getAllSuperAdmin();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{superAdminId}")
    public ResponseEntity<SuperAdminDto> getSuperAdminById(@PathVariable("superAdminId") Long id) {
        SuperAdminDto det = service.getSuperAdminById(id);
        if (det != null) {
            return ResponseEntity.ok(det);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SuperAdminDto> saveSuperAdmin(@RequestBody SuperAdminDto dto) {
        SuperAdminDto created = service.saveSuperAdmin(dto);
        return ResponseEntity.ok(created);
    }

    // Update
    @PutMapping("/{superAdminId}")
    public ResponseEntity<?> updateSuperAdmin(
            @PathVariable("superAdminId") Long id,
            @RequestBody SuperAdminDto superAdminDto) {
        SuperAdminDto updatedSuperAdmin = service.updateSuperAdmin(id, superAdminDto);
        if (updatedSuperAdmin != null) {
            return ResponseEntity.ok(updatedSuperAdmin);
        }
        return ResponseEntity.notFound().build();
    }
}

