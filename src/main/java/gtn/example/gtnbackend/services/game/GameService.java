package gtn.example.gtnbackend.services.game;

import gtn.example.gtnbackend.domains.Game;
import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.GuessDTO;
import gtn.example.gtnbackend.domains.dtos.NewGameRequestDTO;
import java.util.List;

public interface GameService {

  Game createNewGame(NewGameRequestDTO dto, Player player);

  List<Game> gameHistory(Player player);

  Game checkGuess(GuessDTO guessDTO);
}
