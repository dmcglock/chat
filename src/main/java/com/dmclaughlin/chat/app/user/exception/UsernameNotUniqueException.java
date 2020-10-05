package com.dmclaughlin.chat.app.user.exception;

public class UsernameNotUniqueException extends Exception {
  public UsernameNotUniqueException(String message) {
    super(message);
  }

  public UsernameNotUniqueException(String message, Throwable cause) {
    super(message, cause);
  }
}
