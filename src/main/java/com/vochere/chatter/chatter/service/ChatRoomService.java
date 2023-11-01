package com.vochere.chatter.chatter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vochere.chatter.chatter.models.ChatRoom;
import com.vochere.chatter.chatter.repository.ChatRoomRepository;
import com.vochere.chatter.chatter.repository.MessageRepository;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MessageRepository messageRepository;

    public ChatRoom createChatroom() {
        ChatRoom chatRoom = new ChatRoom();
        return chatRoomRepository.save(chatRoom);
    }

    public void addParticipants(String chatRoomId, List<String> userIds) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));
        chatRoom.getParticipantIds().addAll(userIds);
        chatRoomRepository.save(chatRoom);
    }

    public void removeParticipants(String chatRoomId, List<String> userIds) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));
        chatRoom.getParticipantIds().removeAll(userIds);
        chatRoomRepository.save(chatRoom);
    }

    public void deleteChatroom(String chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
        messageRepository.deleteAllByChatRoomId(chatRoomId);
    }
}
