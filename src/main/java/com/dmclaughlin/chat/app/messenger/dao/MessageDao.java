package com.dmclaughlin.chat.app.messenger.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class MessageDao {
  @Id
  @GeneratedValue
  private Long id;
  private String message;
  private Integer chatRoomId;
  private LocalDate timestamp;
  private Integer senderId;
  private Integer recipientId;

  public MessageDao(String message, Integer chatRoomId, Integer senderId, Integer recipientId) {
    this.message = message;
    this.chatRoomId = chatRoomId;
    this.timestamp = LocalDate.now();
    this.senderId = senderId;
    this.recipientId = recipientId;
  }

  // No-op needed for repository library
  public MessageDao() {
  }
}
