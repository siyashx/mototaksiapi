package com.codesupreme.mototaksiapi.dao.chat;

import com.codesupreme.mototaksiapi.model.admin.Admin;
import com.codesupreme.mototaksiapi.model.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
