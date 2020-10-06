package com.dmclaughlin.chat.app.messenger.service;

import com.dmclaughlin.chat.app.messenger.dao.MessageDao;
import com.dmclaughlin.chat.app.messenger.dao.MessageRepo;
import com.dmclaughlin.chat.app.messenger.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageRepo messageRepo;

  public void sendMessage(MessageDto messageDto) {
    MessageDao messageToSend = new MessageDao(messageDto.getMessage(), messageDto.getChatRoomId(), messageDto.getSenderId(), messageDto.getRecipientId());

    messageRepo.save(messageToSend);
  }

  public List<MessageDao> getMessagesForUser(Integer userId, Integer limit) {
    Long lastThirtyDays = lastThirtyDaysMillis();
    Long currentTime = System.currentTimeMillis();
    if(limit == null) {

      return messageRepo.findAllByRecipientIdAndTimestampBetween(userId, lastThirtyDays, currentTime);
    }
    return messageRepo.findAllByRecipientIdOrderByTimestampDesc(userId)
            .stream()
            .limit(limit)
            .collect(Collectors.toList());
  }

  public List<MessageDao> getAllMessages(Integer limit) {
    Long lastThirtyDays = lastThirtyDaysMillis();
    Long currentTime = System.currentTimeMillis();
    List<MessageDao> allMessages = messageRepo.findAllByTimestampBetween(lastThirtyDays, currentTime);

    if(limit != null) {
      return allMessages
              .stream()
              .limit(limit)
              .collect(Collectors.toList());
    }
    return allMessages;
  }

  private Long lastThirtyDaysMillis() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -30);
    return calendar.getTimeInMillis();
  }

}
