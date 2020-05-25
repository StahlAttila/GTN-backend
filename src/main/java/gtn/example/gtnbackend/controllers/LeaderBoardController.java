package gtn.example.gtnbackend.controllers;

import gtn.example.gtnbackend.domains.GameDifficulty;
import gtn.example.gtnbackend.domains.dtos.LeaderBoardDTO;
import gtn.example.gtnbackend.domains.dtos.RanksResponseDTO;
import gtn.example.gtnbackend.services.leaderboard.LeaderBoardService;
import gtn.example.gtnbackend.services.player.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaderBoardController {

  private PlayerService playerService;
  private LeaderBoardService leaderBoardService;

  @Autowired
  public LeaderBoardController(PlayerService playerService, LeaderBoardService leaderBoardService) {
    this.playerService = playerService;
    this.leaderBoardService = leaderBoardService;
  }

  @GetMapping("/api/leaderboard/{difficulty}")
  public ResponseEntity<?> getEasyLeaderBoard(@PathVariable String difficulty) {
    GameDifficulty gamedifficulty = GameDifficulty.valueOf(difficulty.toUpperCase());
    List<LeaderBoardDTO> playerList = playerService.getRankedLeaderBoard(gamedifficulty);

    return new ResponseEntity<>(playerList, HttpStatus.OK);
  }

  @GetMapping("/api/ranks/{username}")
  public ResponseEntity<?> playerRanks(@PathVariable String username) {
    RanksResponseDTO ranksDTO = leaderBoardService.getPlayerRanks(username);

    return new ResponseEntity<>(ranksDTO, HttpStatus.OK);
  }
}
