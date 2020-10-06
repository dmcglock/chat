package com.dmclaughlin.chat.app.messenger.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
public class MessageDao {
  @Id
  @GeneratedValue
  private Integer id;
  private String message;
  private Integer chatRoomId;
  private Long timestamp;
  private Integer senderId;
  private Integer recipientId;

  public MessageDao(String message, Integer chatRoomId, Integer senderId, Integer recipientId) {
    this.message = message;
    this.chatRoomId = chatRoomId;
    this.timestamp = System.currentTimeMillis();
    this.senderId = senderId;
    this.recipientId = recipientId;
  }

  // No-op needed for repository library
  public MessageDao() {
  }
}
