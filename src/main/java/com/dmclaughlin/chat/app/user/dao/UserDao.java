package com.dmclaughlin.chat.app.user.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UserDao {
  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true)
  private String username;

  private String firstName;

  private String lastName;

  public UserDao(String username, String firstName, String lastName){
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // No-op needed for persistence library
  public UserDao() {
  }
}
