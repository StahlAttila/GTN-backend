package gtn.example.gtnbackend.services.game;

import gtn.example.gtnbackend.domains.Game;
import gtn.example.gtnbackend.domains.GameDifficulty;
import gtn.example.gtnbackend.domains.GameFactory;
import gtn.example.gtnbackend.domains.GameType;
import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.GuessDTO;
import gtn.example.gtnbackend.domains.dtos.NewGameRequestDTO;
import gtn.example.gtnbackend.repositories.GameRepository;
import gtn.example.gtnbackend.services.player.PlayerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

  private GameFactory gameFactory;

  private GameRepository gameRepository;
  private PlayerService playerService;

  @Autowired
  public GameServiceImpl(GameRepository gameRepository, PlayerService playerService) {
    this.gameRepository = gameRepository;
    this.playerService = playerService;
    this.gameFactory = new GameFactory();
  }

  @Override
  public Game createNewGame(NewGameRequestDTO dto, Player player) {
    Game newGame = gameFactory.createGame(dto.getDifficulty(), dto.getGameType());
    newGame.setPlayer(player);
    gameRepository.save(newGame);
    player.getGameList().add(newGame);
    playerService.updatePlayer(player);

    return newGame;
  }

  @Override
  public List<Game> gameHistory(Player player) {

    return gameRepository.findAllByPlayer(player);
  }

  @Override
  public Game checkGuess(GuessDTO guessDTO) {
    //TODO: exception for not having a game if game is null
    Game game = findById(guessDTO.getGameId());
    if (guessDTO.getGuess().equals(game.getNumberToGuess())) {
      game.setFinishedAt(System.currentTimeMillis());
      game.setGameStatus("WON");
      //TODO: probably we should set the players rank score here
      gameRepository.save(game);
      updateRankedScore(game);

      return game;
    } else {
      if (game.getLives() - 1 > 0) {
        if (guessDTO.getGuess() > game.getNumberToGuess()) {
          game.setGuessDirection("The number is lower than " + guessDTO.getGuess());
        } else {
          game.setGuessDirection("The number is higher than " + guessDTO.getGuess());
        }
        game.setLives(game.getLives() - 1);
        gameRepository.save(game);

        return game;
      } else {
        game.setLives(0);
        game.setFinishedAt(System.currentTimeMillis());
        game.setGameStatus("LOST");
        gameRepository.save(game);
        updateRankedScore(game);

        return game;
      }
    }
  }

  private void updateRankedScore(Game game) {
    if (game.getGameType().equals(GameType.RANKED)) {
      Player player = game.getPlayer();
      if (game.getDifficulty().equals(GameDifficulty.EASY)) {
        player.setRankedEasy((player.getRankedEasy() + calculateGameScore(game)) / 2);
        playerService.updatePlayer(player);
      } else if (game.getDifficulty().equals(GameDifficulty.MEDIUM)) {
        player.setRankedMedium((player.getRankedMedium() + calculateGameScore(game)) / 2);
        playerService.updatePlayer(player);
      } else {
        player.setRankedHard((player.getRankedHard() + calculateGameScore(game)) / 2);
        playerService.updatePlayer(player);
      }
    }
  }

  private Integer calculateGameScore(Game game) {
    if (game.getLives() == 0) {
      Player player = game.getPlayer();
      switch (game.getDifficulty()) {
        case EASY:
          return player.getRankedEasy() - (player.getRankedEasy() / 10);
        case MEDIUM:
          return player.getRankedMedium() - (player.getRankedMedium() / 10);
        case HARD:
          return player.getRankedHard() - (player.getRankedHard() / 10);
      }
    }
    Integer score = game.getLives() * 1000;
    Integer timeValue = Math.toIntExact((game.getFinishedAt() - game.getStartedAt()) / 1000);

    return score / timeValue;
  }

  private Game findById(Long id) {
    Optional<Game> game = gameRepository.findById(id);

    return game.orElse(null);
  }
}
