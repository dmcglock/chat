package com.dmclaughlin.chat.app.user.service;

import com.dmclaughlin.chat.app.user.UserController;
import com.dmclaughlin.chat.app.user.dao.UserChatRoomRepo;
import com.dmclaughlin.chat.app.user.dao.UserDao;
import com.dmclaughlin.chat.app.user.dao.UserRepo;
import com.dmclaughlin.chat.app.user.dto.CreateUserDto;
import com.dmclaughlin.chat.app.user.dto.UserDto;
import com.dmclaughlin.chat.app.user.exception.UserNotFoundException;
import com.dmclaughlin.chat.app.user.exception.UsernameNotUniqueException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {

  private final UserRepo userRepo;
  private final UserChatRoomRepo userChatRoomRepo;

  Logger logger = LoggerFactory.getLogger(UserService.class);


  public UserDto createUser(CreateUserDto userDto) throws UsernameNotUniqueException {
    try{
      UserDao user = new UserDao(userDto.getUsername(), userDto.getFirstName(), userDto.getLastName());
      UserDto createdUser = new UserDto(userRepo.save(user));
      return createdUser;
    } catch(DataIntegrityViolationException e) {
      throw new UsernameNotUniqueException(userDto.getUsername() + " can't be saved. Username already exists");
    }
  }

  public List<UserDto> getAllUsers() throws UserNotFoundException {
    Iterable<UserDao> allUsers = userRepo.findAll();
    List<UserDto> transformedAllUsers = new ArrayList<>();

    allUsers.forEach(userDao -> {
      UserDto user = new UserDto(userDao);
      transformedAllUsers.add(user);
    });

    if(transformedAllUsers.isEmpty()) {
      throw new UserNotFoundException("No users found");
    }
    return transformedAllUsers;
  }

  public void deleteUser(Integer userId) throws EmptyResultDataAccessException {
    userRepo.deleteById(userId);
    userChatRoomRepo.deleteByUserId(userId);
  }

  public UserDto getUserById(Integer userId) throws UserNotFoundException {
    UserDao userDao = userRepo.getById(userId);
    if(userDao == null) {
      throw new UserNotFoundException("Could not find user with id " + userId);
    }
    return new UserDto(userDao);
  }

}
