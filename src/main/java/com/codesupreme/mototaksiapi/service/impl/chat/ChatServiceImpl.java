package com.codesupreme.mototaksiapi.service.impl.chat;

import com.codesupreme.mototaksiapi.dao.chat.ChatRepository;
import com.codesupreme.mototaksiapi.dto.chat.ChatDto;
import com.codesupreme.mototaksiapi.model.chat.Chat;
import com.codesupreme.mototaksiapi.service.inter.chat.ChatServiceInter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatServiceInter {

    private final ChatRepository chatRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, ModelMapper modelMapper) {
        this.chatRepository = chatRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ChatDto createChat(ChatDto chatDto) {
        Chat chat = modelMapper.map(chatDto, Chat.class);
        chat = chatRepository.save(chat);

        return modelMapper.map(chat, ChatDto.class);
    }

    @Override
    public List<ChatDto> getAllChats() {
        List<Chat> chats = chatRepository.findAll();
        return chats.stream()
                .map(chat -> modelMapper.map(chat, ChatDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChatDto getChatById(Long chatId) {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        return chatOptional.map(chat -> modelMapper.map(chat, ChatDto.class)).orElse(null);
    }

    @Override
    public ChatDto updateChat(Long chatId, ChatDto chatDto) {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();

            // Update fields only if they are not null
            if (chatDto.getCourierId() != null) {
                chat.setCourierId(chatDto.getCourierId());
            }

            if (chatDto.getCustomerId() != null) {
                chat.setCustomerId(chatDto.getCustomerId());
            }

            if (chatDto.getIsReadIds() != null) {
                chat.setIsReadIds(chatDto.getIsReadIds());
            }

            if (chatDto.getMessage() != null) {
                chat.setMessage(chatDto.getMessage());
            }

            if (chatDto.getTimestamp() != null) {
                chat.setTimestamp(chatDto.getTimestamp());
            }


            chat = chatRepository.save(chat);

            return modelMapper.map(chat, ChatDto.class);
        }
        return null;
    }

    @Override
    public Boolean removeById(Long chatId) {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        if (chatOptional.isPresent()) {
            chatRepository.delete(chatOptional.get());

            return true;
        }
        return false;
    }

}