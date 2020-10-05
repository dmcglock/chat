package com.dmclaughlin.chat.app.user.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserDto {

  @NotNull
  String username;

  @NotNull
  String firstName;

  @NotNull
  String lastName;
}
