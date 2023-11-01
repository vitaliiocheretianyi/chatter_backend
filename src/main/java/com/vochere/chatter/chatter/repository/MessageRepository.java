package com.vochere.chatter.chatter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.vochere.chatter.chatter.models.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    void deleteAllByChatRoomId(String chatRoomId);
}
