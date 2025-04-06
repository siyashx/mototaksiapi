package com.codesupreme.mototaksiapi.dao.superadmin;

import com.codesupreme.mototaksiapi.model.superadmin.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
}
