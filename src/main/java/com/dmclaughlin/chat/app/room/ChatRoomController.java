package com.dmclaughlin.chat.app.room;

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
      Integer chatRoomId = chatRoomService.getOrCreateChatRoom(senderId, recipientId);
      return ResponseEntity.ok(chatRoomId);
  }
}
