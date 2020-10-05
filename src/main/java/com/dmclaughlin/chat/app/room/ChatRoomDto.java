package com.dmclaughlin.chat.app.room;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRoomDto {
  private Integer chatRoomId;
  private Integer senderUserId;
  private Integer recipientUserId;
}
