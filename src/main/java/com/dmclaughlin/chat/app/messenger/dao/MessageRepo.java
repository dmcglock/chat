package com.dmclaughlin.chat.app.messenger.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<MessageDao, Integer> {

  List<MessageDao> findAllByRecipientIdAndTimestampBetween(Integer recipientId, Long timestampStart, Long timestampEnd);

  List<MessageDao> findAllByRecipientIdOrderByTimestampDesc(Integer recipientId);

  List<MessageDao> findAllByTimestampBetween(Long timestampStart, Long timestampEnd);

  @Query(value = "select message from MessageDao where recipientId = :userId order by id desc LIMIT :limit", nativeQuery = true)
  List<MessageDao> getAllMessagesForUser(Integer userId, Integer limit);
}
