package gtn.example.gtnbackend.services.leaderboard;

import gtn.example.gtnbackend.domains.dtos.RanksResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface LeaderBoardService {

  RanksResponseDTO getPlayerRanks(String username);
}
