package com.vochere.chatter.chatter.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vochere.chatter.chatter.models.ChatRoom;
import com.vochere.chatter.chatter.service.ChatRoomService;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping
    public ChatRoom createChatroom() {
        return chatRoomService.createChatroom();
    }

    @PostMapping("/{chatRoomId}/participants")
    public ResponseEntity<?> addParticipantsToChatroom(@PathVariable String chatRoomId,
            @RequestBody List<String> userIds) {
        chatRoomService.addParticipants(chatRoomId, userIds);
        return ResponseEntity.ok("Participants added successfully");
    }

    @DeleteMapping("/{chatRoomId}/participants")
    public ResponseEntity<?> removeParticipantsFromChatroom(@PathVariable String chatRoomId,
            @RequestBody List<String> userIds) {
        chatRoomService.removeParticipants(chatRoomId, userIds);
        return ResponseEntity.ok("Participants removed successfully");
    }

    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<?> deleteChatroom(@PathVariable String chatRoomId) {
        chatRoomService.deleteChatroom(chatRoomId);
        return ResponseEntity.ok("Chatroom and related messages deleted successfully");
    }
}
