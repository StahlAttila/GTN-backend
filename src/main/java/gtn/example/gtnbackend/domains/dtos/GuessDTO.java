package gtn.example.gtnbackend.domains.dtos;

public class GuessDTO {

  private Long gameId;
  private Integer guess;

  public GuessDTO() {
  }

  public GuessDTO(Long gameId, Integer guess) {
    this.gameId = gameId;
    this.guess = guess;
  }

  public Long getGameId() {
    return gameId;
  }

  public void setGameId(Long gameId) {
    this.gameId = gameId;
  }

  public Integer getGuess() {
    return guess;
  }

  public void setGuess(Integer guess) {
    this.guess = guess;
  }
}
