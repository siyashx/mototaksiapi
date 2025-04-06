package com.codesupreme.mototaksiapi.dao.admin;

import com.codesupreme.mototaksiapi.model.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminByPhoneNumber(String phoneNumber);

}
