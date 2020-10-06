package com.dmclaughlin.chat.app;

import com.dmclaughlin.chat.app.room.dto.ChatRoomDto;
import com.dmclaughlin.chat.app.user.dao.UserChatRoomRepo;
import com.dmclaughlin.chat.app.user.dao.UserRepo;
import com.dmclaughlin.chat.app.user.dto.CreateUserDto;
import com.dmclaughlin.chat.app.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

  @Mock
  UserRepo userRepo;

  @Mock
  UserChatRoomRepo userChatRoomRepo;

  @InjectMocks
  UserService userService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void successfulCreateUser() {
    //given
    CreateUserDto givenUser = new CreateUserDto(1,2,);
  }


}
