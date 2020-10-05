package com.dmclaughlin.chat.app.messenger.controller;

import com.dmclaughlin.chat.app.messenger.dao.MessageDao;
import com.dmclaughlin.chat.app.messenger.dto.MessageDto;
import com.dmclaughlin.chat.app.messenger.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/messenger")
@RequiredArgsConstructor
public class MessengerController {

  private final MessageService messageService;

  @PostMapping("/send")
  public ResponseEntity sendMessageToUser(
          @RequestBody @Valid MessageDto messageDto) {
    messageService.sendMessage(messageDto);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/{userId}/all")
  public ResponseEntity getAllMessagesForUser(@PathVariable("userId") Integer userId,
                                              @RequestParam(value = "limit", required = false) Integer limit) {
    List<MessageDao> messages = messageService.getMessagesForUser(userId, limit);
    if(messages == null || messages.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(messages);
  }

  @GetMapping("/all")
  public ResponseEntity getAllMessages(@RequestParam(value = "limit", required = false) Integer limit) {
    List<MessageDao> messages  = messageService.getAllMessages(limit);
    if(messages == null || messages.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(messages);
  }
}
