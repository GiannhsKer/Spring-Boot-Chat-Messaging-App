package com.gi.cryptoChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration.class
})
@EnableCassandraRepositories(basePackages = "com.gi.cryptoChat.repository")
public class CryptoChatMessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoChatMessagingApplication.class, args);
	}

}
