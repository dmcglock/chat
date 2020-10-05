package com.dmclaughlin.chat.app.room.dao;

import com.dmclaughlin.chat.app.room.dao.ChatRoomDao;
import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRepo extends CrudRepository<ChatRoomDao, Integer> {

}
