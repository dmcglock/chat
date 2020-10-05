package com.dmclaughlin.chat.app.user.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserChatRoomRepo extends CrudRepository<UserChatRoomDao, Integer> {
  @Transactional
  Long deleteByUserId(Integer userId);

  @Query("select a.chatRoomId from UserChatRoomDao AS a join UserChatRoomDao as b on a.chatRoomId = b.chatRoomId where a.userId = :senderId and b.userId = :recipientId")
  Integer selectCommonChatRoom(Integer senderId, Integer recipientId);
}
