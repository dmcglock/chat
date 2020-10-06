package com.dmclaughlin.chat.app.room.service;

import com.dmclaughlin.chat.app.room.dao.ChatRoomDao;
import com.dmclaughlin.chat.app.room.dao.ChatRoomRepo;
import com.dmclaughlin.chat.app.room.dto.ChatRoomDto;
import com.dmclaughlin.chat.app.user.dao.UserChatRoomDao;
import com.dmclaughlin.chat.app.user.dao.UserChatRoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

  private final UserChatRoomRepo userChatRoomRepo;
  private final ChatRoomRepo chatRoomRepo;

  public ChatRoomDto getOrCreateChatRoom(Integer senderUserId, Integer recipientUserId) {
    Integer sharedChatRoom = userChatRoomRepo.selectCommonChatRoom(senderUserId, recipientUserId);
    if(sharedChatRoom == null) {
      return createChatRoom(senderUserId, recipientUserId);
    }
    return findSharedChatRoom(sharedChatRoom, senderUserId, recipientUserId);
  }

  public ChatRoomDto updateRoomPurpose(Integer chatRoomId, String purpose) {
    ChatRoomDao currentChatRoom = new ChatRoomDao(chatRoomId, purpose);
    chatRoomRepo.save(currentChatRoom);

    return new ChatRoomDto(currentChatRoom);
  }

  private ChatRoomDto createChatRoom(Integer senderId, Integer recipientId) {
    ChatRoomDao createdChatRoom = chatRoomRepo.save(new ChatRoomDao());
    UserChatRoomDao senderUserChatRoom = UserChatRoomDao
            .builder()
            .chatRoomId(createdChatRoom.getChatRoomId())
            .userId(senderId)
            .build();

    UserChatRoomDao recipientUserChatRoom = UserChatRoomDao
            .builder()
            .chatRoomId(createdChatRoom.getChatRoomId())
            .userId(recipientId)
            .build();

    userChatRoomRepo.save(senderUserChatRoom);
    userChatRoomRepo.save(recipientUserChatRoom);

    return new ChatRoomDto(createdChatRoom, senderId, recipientId);
  }

  private ChatRoomDto findSharedChatRoom(Integer sharedChatRoomId, Integer senderId, Integer recipientId) {
    Optional<ChatRoomDao> sharedChatRoom = chatRoomRepo.findById(sharedChatRoomId);
    // Shouldn't need the else, but if we do, create the room
    return sharedChatRoom
            .map(chatRoomDao -> new ChatRoomDto(chatRoomDao, senderId, recipientId))
            .orElseGet(() -> createChatRoom(senderId, recipientId));
  }
}
