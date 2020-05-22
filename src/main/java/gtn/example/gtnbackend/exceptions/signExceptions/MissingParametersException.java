package gtn.example.gtnbackend.exceptions.signExceptions;

import gtn.example.gtnbackend.domains.ErrorMessage;
import gtn.example.gtnbackend.domains.dtos.RequestDTO;
import java.util.List;

public class MissingParametersException extends PlayerException {

  private RequestDTO requestDTO;

  public MissingParametersException(
      RequestDTO requestDTO) {
    this.requestDTO = requestDTO;
  }

  @Override
  public ErrorMessage getErrorMessage() {
    return new ErrorMessage("error", buildMessage());
  }

  private String buildMessage() {
    List<String> nullFields = requestDTO.getNullFields();
    String fields = String.join(", ", nullFields);

    return "Missing field(s): " + fields + "!";
  }
}
