package com.dmclaughlin.chat.app.user;

import com.dmclaughlin.chat.app.user.dao.UserDao;
import com.dmclaughlin.chat.app.user.dto.CreateUserDto;
import com.dmclaughlin.chat.app.user.dto.UserDto;
import com.dmclaughlin.chat.app.user.exception.UserNotFoundException;
import com.dmclaughlin.chat.app.user.exception.UsernameNotUniqueException;
import com.dmclaughlin.chat.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  Logger logger = LoggerFactory.getLogger(UserController.class);

  @PostMapping(value = "/create")
  public ResponseEntity createUser(
          @RequestBody @Valid CreateUserDto createUserDto) {
    try {
      UserDto savedUser = userService.createUser(createUserDto);

      return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    } catch(UsernameNotUniqueException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @GetMapping("/all")
  public ResponseEntity getAllUsers() {
    try {
      List<UserDto> allUsers = userService.getAllUsers();
      return ResponseEntity.ok(allUsers);
    } catch (UserNotFoundException e) {
      logger.error("No users found");
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{userId}")
  public ResponseEntity getUserById(@PathVariable("userId") Integer userId) {
    try {
      UserDto user = userService.getUserById(userId);
      return ResponseEntity.ok(user);
    } catch (UserNotFoundException e) {
      logger.warn("Could not find user with id: {}", userId);
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity deleteUserById(@PathVariable("userId") Integer userId) {
    try{
      userService.deleteUser(userId);
      return ResponseEntity.ok().build();
    } catch(EmptyResultDataAccessException e) {
      return ResponseEntity.notFound().build();
    }
  }
}