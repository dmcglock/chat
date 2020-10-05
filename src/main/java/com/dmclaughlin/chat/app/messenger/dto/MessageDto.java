package com.dmclaughlin.chat.app.messenger.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class MessageDto {
  @NotNull
  @Max(255)
  String message;

  @NotNull
  Integer chatRoomId;

  @NotNull
  Integer toUserId;

  @NotNull
  Integer fromUserId;
}
