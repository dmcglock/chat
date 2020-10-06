package com.dmclaughlin.chat.app;

import com.dmclaughlin.chat.app.room.dao.ChatRoomDao;
import com.dmclaughlin.chat.app.room.dao.ChatRoomRepo;
import com.dmclaughlin.chat.app.room.dto.ChatRoomDto;
import com.dmclaughlin.chat.app.room.service.ChatRoomService;
import com.dmclaughlin.chat.app.user.dao.UserChatRoomRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


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
}
