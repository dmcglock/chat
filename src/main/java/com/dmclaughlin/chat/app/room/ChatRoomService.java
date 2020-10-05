package com.dmclaughlin.chat.app.room;

import com.dmclaughlin.chat.app.user.dao.UserChatRoomDao;
import com.dmclaughlin.chat.app.user.dao.UserChatRoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

  private final UserChatRoomRepo userChatRoomRepo;

  public Integer getOrCreateChatRoom(Integer senderUserId, Integer recipientUserId) {
    Integer sharedChatRoomId = userChatRoomRepo.selectCommonChatRoom(senderUserId, recipientUserId);
    if(sharedChatRoomId == null) {
      return createChatRoom(senderUserId, recipientUserId);
    }
    return sharedChatRoomId;
  }

  private Integer createChatRoom(Integer senderId, Integer recipientId) {
    ChatRoomDao createdChatRoom = new ChatRoomDao();
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

    return createdChatRoom.getChatRoomId();
  }
}
