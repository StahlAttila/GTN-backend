package gtn.example.gtnbackend.domains.dtos;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SignUpRequestDTO extends RequestDTO{

  private String email;

  public SignUpRequestDTO() {
  }

  public SignUpRequestDTO(String username, String password, String email) {
    super(username, password);
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /*public List<String> getNullFields() {
    List<String> nullFields = new ArrayList<>();
    for (Field f : getClass().getDeclaredFields()) {
      try {
        if (f.get(this) == null) {
          nullFields.add(f.getName());
        }
      } catch (IllegalAccessException ignored) {
      }
    }

    return nullFields;
  }*/
}
