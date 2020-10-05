package com.dmclaughlin.chat.app.messenger.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<MessageDao, Integer> {

  @Query("select message from MessageDao where recipientId = :userId and timestamp > :lastThirtyDays")
  List<MessageDao> getLastThirtyDaysForUser(Integer userId, String lastThirtyDays);

  @Query("select message from MessageDao where timestamp > :lastThirtyDays")
  List<MessageDao> getAllMessagesWithinLastThirtyDays(String lastThirtyDays);

  @Query(value = "select * from MessageDao order by id desc LIMIT :limit", nativeQuery = true)
  List<MessageDao> getAllMessages();

  @Query(value = "select * from MessageDao where recipientId = :userId order by id desc LIMIT :limit", nativeQuery = true)
  List<MessageDao> getAllMessagesForUser(Integer userId, Integer limit);
}
