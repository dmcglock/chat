package com.dmclaughlin.chat.app.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class CreateUserDto {

  @NotNull
  String username;

  @NotNull
  String firstName;

  @NotNull
  String lastName;
}
