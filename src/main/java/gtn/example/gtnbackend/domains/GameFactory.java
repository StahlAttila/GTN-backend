package gtn.example.gtnbackend.domains;

import java.util.Random;

public class GameFactory {

  public Game createGame(String difficulty, String type) {
    GameType gameType = GameType.valueOf(type.toUpperCase());
    GameDifficulty gameDifficulty = GameDifficulty.valueOf(difficulty.toUpperCase());
    switch (gameType) {
      case CASUAL:
        return new Game(getCurrentTime(), gameDifficulty, GameType.CASUAL,
            setNumberToGuess(gameDifficulty), setLivesAmount(gameDifficulty));
      case CUSTOM:
        return new Game(getCurrentTime(), gameDifficulty, GameType.CUSTOM,
            setNumberToGuess(gameDifficulty), setLivesAmount(gameDifficulty));
      case RANKED:
        return new Game(getCurrentTime(), gameDifficulty, GameType.RANKED,
            setNumberToGuess(gameDifficulty), setLivesAmount(gameDifficulty));
      default:
        return null;
    }
  }

  private Long getCurrentTime() {
    return System.currentTimeMillis();
  }

  private Integer randomNumber(Integer bound) {
    Random random = new Random();

    return random.nextInt(bound);
  }

  private Integer setLivesAmount(GameDifficulty difficulty) {
    if (difficulty.equals(GameDifficulty.EASY)) {
      return 3;
    } else if (difficulty.equals(GameDifficulty.MEDIUM)) {
      return 7;
    } else {
      return 10;
    }
  }

  private Integer setNumberToGuess(GameDifficulty difficulty) {
    if (difficulty.equals(GameDifficulty.EASY)) {
      return randomNumber(21);
    } else if (difficulty.equals(GameDifficulty.MEDIUM)) {
      return randomNumber(101);
    } else {
      return randomNumber(501);
    }
  }

}
