package gtn.example.gtnbackend.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Game {

  @Id
  @GeneratedValue
  private Long id;
  private Long startedAt;
  private Long finishedAt;
  private GameDifficulty difficulty;
  private GameType gameType;
  private Integer numberToGuess;
  private Integer lives;
  private String gameStatus;
  private String guessDirection;

  @ManyToOne
  @JsonBackReference
  private Player player;

  public Game() {
  }

  public Game(Long startedAt, GameDifficulty difficulty,
      GameType gameType, Integer numberToGuess, Integer lives) {
    this.startedAt = startedAt;
    this.difficulty = difficulty;
    this.gameType = gameType;
    this.numberToGuess = numberToGuess;
    this.lives = lives;
  }

  //different constructor for custom game
 /* public Game(GameDifficulty difficulty, GameType gameType, Integer numberToGuess, Player player) {
    this.difficulty = difficulty;
    this.gameType = gameType;
    this.numberToGuess = numberToGuess;
    this.player = player;
  }*/

  public String getGuessDirection() {
    return guessDirection;
  }

  public void setGuessDirection(String guessDirection) {
    this.guessDirection = guessDirection;
  }

  public String getGameStatus() {
    return gameStatus;
  }

  public void setGameStatus(String gameStatus) {
    this.gameStatus = gameStatus;
  }

  public Integer getLives() {
    return lives;
  }

  public void setLives(Integer lives) {
    this.lives = lives;
  }

  public GameType getGameType() {
    return gameType;
  }

  public void setGameType(GameType gameType) {
    this.gameType = gameType;
  }

  public Integer getNumberToGuess() {
    return numberToGuess;
  }

  public void setNumberToGuess(Integer numberToGuess) {
    this.numberToGuess = numberToGuess;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(Long startedAt) {
    this.startedAt = startedAt;
  }

  public Long getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(Long finishedAt) {
    this.finishedAt = finishedAt;
  }

  public GameDifficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(GameDifficulty difficulty) {
    this.difficulty = difficulty;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
