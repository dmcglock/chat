package com.dmclaughlin.chat.app;

import com.dmclaughlin.chat.app.room.dao.ChatRoomDao;
import com.dmclaughlin.chat.app.room.dao.ChatRoomRepo;
import com.dmclaughlin.chat.app.room.dto.ChatRoomDto;
import com.dmclaughlin.chat.app.room.service.ChatRoomService;
import com.dmclaughlin.chat.app.user.dao.UserChatRoomDao;
import com.dmclaughlin.chat.app.user.dao.UserChatRoomRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


public class ChatRoomServiceTest {

  @Mock
  UserChatRoomRepo userChatRoomRepo;

  @Mock
  ChatRoomRepo chatRoomRepo;

  @InjectMocks
  ChatRoomService chatRoomService;

  @BeforeEach
   public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getRoomWhenPresentTest() {
    //given
    ChatRoomDao returnedChatRoom = new ChatRoomDao();
    ChatRoomDto expectedChatRoom = new ChatRoomDto(returnedChatRoom);

    // when
    when(userChatRoomRepo.selectCommonChatRoom(1,2)).thenReturn(1);
    when(chatRoomRepo.findById(anyInt())).thenReturn(Optional.of(returnedChatRoom));
    ChatRoomDto result = chatRoomService.getOrCreateChatRoom(1, 2);

    //then
    assert expectedChatRoom.getChatRoomId() == result.getChatRoomId();
    assert expectedChatRoom.getPurpose().equals(result.getPurpose());
  }

  @Test
  public void createRoomWhenNotPresent() {
    //given
    ChatRoomDao returnedChatRoom = new ChatRoomDao();
    ChatRoomDto expectedChatRoom = new ChatRoomDto(returnedChatRoom);
    UserChatRoomDao dummyChatRoom = UserChatRoomDao.builder()
            .chatRoomId(returnedChatRoom.getChatRoomId())
            .userId(2)
            .build();

    // when
    when(userChatRoomRepo.selectCommonChatRoom(anyInt(),anyInt())).thenReturn(null);
    when(chatRoomRepo.findById(anyInt())).thenReturn(Optional.of(returnedChatRoom));
    when(chatRoomRepo.save(any(ChatRoomDao.class))).thenReturn(returnedChatRoom);
    when(userChatRoomRepo.save(dummyChatRoom)).thenReturn(dummyChatRoom);
    ChatRoomDto result = chatRoomService.getOrCreateChatRoom(1, 2);

    //then
    verify(userChatRoomRepo, times(2)).save(any(UserChatRoomDao.class));
    assert expectedChatRoom.getChatRoomId() == result.getChatRoomId();
    assert expectedChatRoom.getPurpose().equals(result.getPurpose());
  }

  @Test
  public void updateRoomPurposeTest() {
    //given
    Integer givenChatRoomId = 12;
    String givenPurpose = "We like to chat";
    ChatRoomDao returnedChatRoom = new ChatRoomDao(givenChatRoomId, givenPurpose);

    // when
    ChatRoomDto result = chatRoomService.updateRoomPurpose(givenChatRoomId, givenPurpose);
    verify(chatRoomRepo, times(1)).save(returnedChatRoom);

    //then
    assert result.getPurpose().equals(givenPurpose);
    assert result.getChatRoomId() == givenChatRoomId;
  }
}
