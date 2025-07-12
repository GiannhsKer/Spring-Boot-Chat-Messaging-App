package com.gi.cryptoChat.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gi.cryptoChat.model.OnlineUser;

@Repository
public interface OnlineUserRepository extends CrudRepository<OnlineUser, String> {

}
