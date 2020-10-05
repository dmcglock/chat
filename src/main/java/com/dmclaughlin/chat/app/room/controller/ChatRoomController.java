package com.dmclaughlin.chat.app.room.controller;

import com.dmclaughlin.chat.app.room.dto.ChatRoomDto;
import com.dmclaughlin.chat.app.room.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  @GetMapping(value = "/room", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getChatRoom(@RequestParam("senderId") Integer senderId,
                                    @RequestParam("recipientId") Integer recipientId) {
      ChatRoomDto chatRoom = chatRoomService.getOrCreateChatRoom(senderId, recipientId);
      return ResponseEntity.ok(chatRoom);
  }

  @PostMapping("/room/{chatRoomId}")
  public ResponseEntity updateRoomPurpose(@PathVariable("chatRoomId") Integer chatRoomId,
                                          @RequestParam(value = "purpose", required = true) String purpose) {

  }
}
