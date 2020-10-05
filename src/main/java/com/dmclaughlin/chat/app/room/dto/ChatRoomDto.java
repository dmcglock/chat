package com.dmclaughlin.chat.app.room.dto;

import com.dmclaughlin.chat.app.room.dao.ChatRoomDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChatRoomDto {
  private Integer chatRoomId;
  private Integer senderUserId;
  private Integer recipientUserId;
  private String purpose;

  public ChatRoomDto(ChatRoomDao chatRoomDao, Integer senderId, Integer recipientId){
    this.chatRoomId = chatRoomDao.getChatRoomId();
    this.purpose = chatRoomDao.getPurpose();
    this.senderUserId = senderId;
    this.recipientUserId = recipientId;
  }

  public ChatRoomDto(ChatRoomDao chatRoomDao) {
    this.chatRoomId = chatRoomDao.getChatRoomId();
    this.purpose = chatRoomDao.getPurpose();
  }
}
