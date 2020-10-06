package com.dmclaughlin.chat.app;

import com.dmclaughlin.chat.app.user.dao.UserChatRoomRepo;
import com.dmclaughlin.chat.app.user.dao.UserDao;
import com.dmclaughlin.chat.app.user.dao.UserRepo;
import com.dmclaughlin.chat.app.user.dto.CreateUserDto;
import com.dmclaughlin.chat.app.user.dto.UserDto;
import com.dmclaughlin.chat.app.user.exception.UsernameNotUniqueException;
import com.dmclaughlin.chat.app.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    CreateUserDto givenUser = CreateUserDto.builder()
            .firstName("Scott")
            .lastName("Pilgrim")
            .username("NegaScott")
            .build();
    UserDao savedUser = new UserDao(givenUser.getUsername(), givenUser.getFirstName(), givenUser.getLastName());
    UserDto result = null;

    //when
    when(userRepo.save(any(UserDao.class))).thenReturn(savedUser);
    try {
      result = userService.createUser(givenUser);
    } catch (UsernameNotUniqueException e) {
      //do nothing
    }

    //then
    assert result != null;
    assert result.getFirstName().equals(savedUser.getFirstName());
    assert result.getLastName().equals(savedUser.getLastName());
    assert result.getUsername().equals(savedUser.getUsername());
  }

  @Test
  public void exceptionOnUserCreation() {
    //given
    CreateUserDto givenUser = CreateUserDto.builder()
            .firstName("Scott")
            .lastName("Pilgrim")
            .username("NegaScott")
            .build();

    //when
    when(userRepo.save(any(UserDao.class))).thenThrow(UsernameNotUniqueException.class);

    //then
    Assertions.assertThrows(UsernameNotUniqueException.class, () -> {
      userService.createUser(givenUser);
    });
  }


}
