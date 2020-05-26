package gtn.example.gtnbackend.services.player;

import gtn.example.gtnbackend.domains.GameDifficulty;
import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.LeaderBoardDTO;
import gtn.example.gtnbackend.domains.dtos.SignInRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignInResponseDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpResponseDTO;
import gtn.example.gtnbackend.exceptions.signExceptions.MissingParametersException;
import gtn.example.gtnbackend.exceptions.signExceptions.UsernameAlreadyTakenException;
import gtn.example.gtnbackend.exceptions.signExceptions.WrongUsernameOrPasswordException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {

  Player findByName(String username);

  SignUpResponseDTO signUpPlayer(SignUpRequestDTO signUpDTO)
      throws UsernameAlreadyTakenException, MissingParametersException;

  SignInResponseDTO signInPlayer(SignInRequestDTO signInRequestDTO)
      throws MissingParametersException, WrongUsernameOrPasswordException;

  void updatePlayer(Player player);

  List<LeaderBoardDTO> getRankedLeaderBoard(GameDifficulty gameDifficulty);

  Boolean doesItHaveEnoughGames(GameDifficulty difficulty, Player player);
}
