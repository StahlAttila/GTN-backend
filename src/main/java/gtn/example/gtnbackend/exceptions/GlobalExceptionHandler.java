package gtn.example.gtnbackend.exceptions;

import gtn.example.gtnbackend.exceptions.signExceptions.MissingParametersException;
import gtn.example.gtnbackend.exceptions.signExceptions.UsernameAlreadyTakenException;
import gtn.example.gtnbackend.exceptions.signExceptions.WrongUsernameOrPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  public GlobalExceptionHandler() {
  }

  @ExceptionHandler(value = MissingParametersException.class)
  public ResponseEntity<?> missingParametersException(MissingParametersException e) {
    return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = UsernameAlreadyTakenException.class)
  public ResponseEntity<?> usernameAlreadyTakenException(UsernameAlreadyTakenException e) {
    return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.OK);
  }

  @ExceptionHandler(value = WrongUsernameOrPasswordException.class)
  public ResponseEntity<?> wrongUsernameOrPasswordException(WrongUsernameOrPasswordException e) {
    return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.OK);
  }

}
