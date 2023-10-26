package com.vochere.chatter.chatter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatRooms")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
    @Id
    private String id;
    private List<String> participantIds; // List of userIds participating in the chat
    // in future add other fields like chatRoomName, createdAt, etc.
}
