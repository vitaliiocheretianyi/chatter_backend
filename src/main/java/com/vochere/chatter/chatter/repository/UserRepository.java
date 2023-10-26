package com.vochere.chatter.chatter.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.vochere.chatter.chatter.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    // Additional query methods if needed
    Optional<User> findByUsername(String username);
}
