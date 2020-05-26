package gtn.example.gtnbackend.exceptions.signExceptions;

import gtn.example.gtnbackend.domains.ErrorMessage;

public abstract class PlayerException extends Exception {

  public abstract ErrorMessage getErrorMessage();
}
