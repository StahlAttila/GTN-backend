package gtn.example.gtnbackend.domains.dtos;

public class SignInRequestDTO extends RequestDTO {

  public SignInRequestDTO() {

  }

  public SignInRequestDTO(String username, String password) {
    super(username, password);
  }
}
