package gtn.example.gtnbackend.domains.dtos;

public class NewGameRequestDTO {

  private String gameType;
  private String difficulty;

  public NewGameRequestDTO() {
  }

  public NewGameRequestDTO(String gameType, String difficulty) {
    this.gameType = gameType;
    this.difficulty = difficulty;
  }

  public String getGameType() {
    return gameType;
  }

  public void setGameType(String gameType) {
    this.gameType = gameType;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }
}
