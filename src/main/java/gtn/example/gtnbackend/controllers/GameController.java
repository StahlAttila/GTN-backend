package gtn.example.gtnbackend.controllers;

import gtn.example.gtnbackend.domains.Game;
import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.GuessDTO;
import gtn.example.gtnbackend.domains.dtos.NewGameRequestDTO;
import gtn.example.gtnbackend.services.game.GameService;
import gtn.example.gtnbackend.services.player.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

  private GameService gameService;
  private PlayerService playerService;

  @Autowired
  public GameController(GameService gameService, PlayerService playerService) {
    this.gameService = gameService;
    this.playerService = playerService;
  }

  //TODO: create a response DTO
  @PostMapping("/api/game/new")
  public ResponseEntity<?> newGame(@RequestBody NewGameRequestDTO newGameRequestDTO) {
    Game newGame = gameService.createNewGame(newGameRequestDTO, getCurrentPlayer());

    return new ResponseEntity<>(newGame, HttpStatus.OK);
  }

  //TODO: create a response DTO
  @GetMapping("/api/game/history")
  public ResponseEntity<?> gameHistory() {
    List<Game> gameList = gameService.gameHistory(getCurrentPlayer());
    return new ResponseEntity<>(gameList, HttpStatus.OK);
  }

  @PostMapping("/api/game/guess")
  public ResponseEntity<?> guess(@RequestBody GuessDTO guessDTO) {
    Game game = gameService.checkGuess(guessDTO);

    return new ResponseEntity<>(game, HttpStatus.OK);
  }

  private Player getCurrentPlayer() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return playerService.findByName(username);
  }


}
