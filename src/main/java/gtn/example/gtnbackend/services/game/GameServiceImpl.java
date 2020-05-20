package gtn.example.gtnbackend.services.game;

import gtn.example.gtnbackend.domains.Game;
import gtn.example.gtnbackend.domains.GameFactory;
import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.GuessDTO;
import gtn.example.gtnbackend.domains.dtos.NewGameRequestDTO;
import gtn.example.gtnbackend.repositories.GameRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

  private GameFactory gameFactory;

  private GameRepository gameRepository;

  @Autowired
  public GameServiceImpl(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
    this.gameFactory = new GameFactory();
  }

  @Override
  public Game createNewGame(NewGameRequestDTO dto, Player player) {
    Game newGame = gameFactory.createGame(dto.getDifficulty(), dto.getGameType());
    newGame.setPlayer(player);
    gameRepository.save(newGame);

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

      return game;
    } else {
      if (game.getLives() - 1 > 0) {
        if(guessDTO.getGuess() > game.getNumberToGuess()) {
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

        return game;
      }
    }
  }

  private Game findById(Long id) {
    Optional<Game> game = gameRepository.findById(id);

    return game.orElse(null);
  }
}
