package gtn.example.gtnbackend.exceptions.signExceptions;

import gtn.example.gtnbackend.domains.ErrorMessage;

public class WrongUsernameOrPasswordException extends PlayerException {

  @Override
  public ErrorMessage getErrorMessage() {
    return new ErrorMessage("error", "Wrong username or password!");
  }
}
