package com.dmclaughlin.chat.app.messenger.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class MessageDto {
  @Size(min = 1, max = 255)
  @NotNull
  String message;

  @NotNull
  Integer chatRoomId;

  @NotNull
  Integer senderId;

  @NotNull
  Integer recipientId;
}
