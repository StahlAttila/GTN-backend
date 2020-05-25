package gtn.example.gtnbackend.services.leaderboard;

import gtn.example.gtnbackend.domains.GameDifficulty;
import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.RanksResponseDTO;
import gtn.example.gtnbackend.services.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

  private PlayerService playerService;

  @Autowired
  public LeaderBoardServiceImpl(PlayerService playerService) {
    this.playerService = playerService;
  }

  @Override
  public RanksResponseDTO getPlayerRanks(String username) {
    Player player = playerService.findByName(username);
    RanksResponseDTO responseDTO = new RanksResponseDTO();
    if (playerService.doesItHaveEnoughGames(GameDifficulty.EASY, player)) {
      responseDTO.setRankedEasy(player.getRankedEasy().toString());
    }
    if (playerService.doesItHaveEnoughGames(GameDifficulty.MEDIUM, player)) {
      responseDTO.setRankedMedium(player.getRankedMedium().toString());
    }
    if (playerService.doesItHaveEnoughGames(GameDifficulty.HARD, player)) {
      responseDTO.setRankedHard(player.getRankedHard().toString());
    }

    return responseDTO;
  }
}
