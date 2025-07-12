package com.gi.cryptoChat.repo;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.gi.cryptoChat.model.ChatMessage;

public interface ChatMessageRepository extends CassandraRepository<ChatMessage, UUID> {

}
