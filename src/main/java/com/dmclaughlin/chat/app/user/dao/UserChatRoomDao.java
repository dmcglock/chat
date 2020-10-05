package com.dmclaughlin.chat.app.user.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
public class UserChatRoomDao {
  @Id
  @GeneratedValue
  private Integer id;

  private Integer userId;

  private Integer chatRoomId;
}
