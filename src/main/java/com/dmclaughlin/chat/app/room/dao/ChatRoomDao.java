package com.dmclaughlin.chat.app.room.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDao {
  @Id
  @GeneratedValue
  private Integer chatRoomId;
  private String purpose = "No purpose set for this chat room";
}
