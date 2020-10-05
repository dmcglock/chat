package com.dmclaughlin.chat.app.room;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ChatRoomDao {
  @Id
  @GeneratedValue
  private Integer chatRoomId;
  private String purpose = "No purpose set for this chat room";
}
