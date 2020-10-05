package com.dmclaughlin.chat.app.room;

import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRepo extends CrudRepository<ChatRoomDao, Integer> {

}
