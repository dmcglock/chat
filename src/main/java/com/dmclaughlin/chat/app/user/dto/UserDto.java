package com.dmclaughlin.chat.app.user.dto;

import com.dmclaughlin.chat.app.user.dao.UserDao;
import lombok.Data;

@Data
public class UserDto {
  private Integer userId;
  private String username;
  private String firstName;
  private String lastName;

  public UserDto(UserDao userDao) {
    this.userId = userDao.getId();
    this.username = userDao.getUsername();
    this.firstName = userDao.getFirstName();
    this.lastName = userDao.getLastName();
  }
}
