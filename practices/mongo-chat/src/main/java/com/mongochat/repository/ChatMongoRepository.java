package com.mongochat.repository;

import com.mongochat.entity.ChatDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ChatMongoRepository extends ReactiveMongoRepository<ChatDocument, ObjectId> {
}
