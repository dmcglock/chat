package com.dmclaughlin.chat.app.messenger.service;

import com.dmclaughlin.chat.app.messenger.dao.MessageDao;
import com.dmclaughlin.chat.app.messenger.dao.MessageRepo;
import com.dmclaughlin.chat.app.messenger.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageRepo messageRepo;

  public void sendMessage(MessageDto messageDto) {
    MessageDao messageToSend = new MessageDao(messageDto.getMessage(), messageDto.getChatRoomId(), messageDto.getFromUserId(), messageDto.getToUserId());

    messageRepo.save(messageToSend);
  }

  public List<MessageDao> getMessagesForUser(Integer userId, Integer limit) {
    if(limit == null) {
      LocalDate lastThirtyDays = LocalDate.now().minusDays(30);
      return messageRepo.getLastThirtyDaysForUser(userId, lastThirtyDays.toString());
    }
    return messageRepo.getAllMessagesForUser(userId, limit);
  }

  public List<MessageDao> getAllMessages(Integer limit) {
    if(limit == null) {
      LocalDate lastThirtyDays = LocalDate.now().minusDays(30);
      return messageRepo.getAllMessagesWithinLastThirtyDays(lastThirtyDays.toString());
    }

    return getAllMessages(limit);
  }

}
