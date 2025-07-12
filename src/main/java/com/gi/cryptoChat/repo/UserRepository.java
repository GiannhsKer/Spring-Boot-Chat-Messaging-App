package com.gi.cryptoChat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gi.cryptoChat.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findUserByUsername(String username);
}
