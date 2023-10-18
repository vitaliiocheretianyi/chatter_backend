package com.vochere.chatter.chatter.src.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private String id;
    private String content;
    private String senderId;
    private String chatRoomId; // or conversationId
    private LocalDateTime timestamp;
    // ... possibly other fields like messageType (text, image, video, etc.)
}
