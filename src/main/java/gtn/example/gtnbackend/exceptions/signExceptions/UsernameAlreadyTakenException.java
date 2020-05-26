package gtn.example.gtnbackend.exceptions.signExceptions;

import gtn.example.gtnbackend.domains.ErrorMessage;

public class UsernameAlreadyTakenException extends PlayerException {

  private String username;

  public UsernameAlreadyTakenException(String username) {
    this.username = username;
  }

  @Override
  public ErrorMessage getErrorMessage() {
    return new ErrorMessage("error", username + " is already taken, please choose another one!");
  }
}
