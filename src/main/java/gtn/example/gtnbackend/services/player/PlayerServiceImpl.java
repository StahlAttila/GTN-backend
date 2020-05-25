package gtn.example.gtnbackend.services.player;

import gtn.example.gtnbackend.domains.GameDifficulty;
import gtn.example.gtnbackend.domains.GameType;
import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.LeaderBoardDTO;
import gtn.example.gtnbackend.domains.dtos.SignInRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignInResponseDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpResponseDTO;
import gtn.example.gtnbackend.exceptions.signExceptions.MissingParametersException;
import gtn.example.gtnbackend.exceptions.signExceptions.UsernameAlreadyTakenException;
import gtn.example.gtnbackend.exceptions.signExceptions.WrongUsernameOrPasswordException;
import gtn.example.gtnbackend.repositories.PlayerRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

  private PlayerRepository playerRepository;
  private ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public PlayerServiceImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Player findByName(String username) {
    Optional<Player> player = playerRepository.findByName(username);

    return player.orElse(null);
  }

  @Override
  public SignUpResponseDTO signUpPlayer(SignUpRequestDTO signUpRequestDTO)
      throws UsernameAlreadyTakenException, MissingParametersException {
    checkUserSignUp(signUpRequestDTO);
    Player player = new Player(signUpRequestDTO.getUsername(), signUpRequestDTO.getEmail(),
        passwordEncoder().encode(signUpRequestDTO.getPassword()));
    playerRepository.save(player);
    return new SignUpResponseDTO(player.getId(), player.getName(), player.getEmail());
  }

  @Override
  public SignInResponseDTO signInPlayer(SignInRequestDTO signInRequestDTO)
      throws MissingParametersException, WrongUsernameOrPasswordException {
    Player player = checkUserSignIn(signInRequestDTO);

    return new SignInResponseDTO(player.getName(), null);
  }

  @Override
  public void updatePlayer(Player player) {
    playerRepository.save(player);
  }

  @Override
  public List<LeaderBoardDTO> getRankedLeaderBoard(GameDifficulty gameDifficulty) {
    List<Player> playerList = (List<Player>) playerRepository.findAll();
    List<LeaderBoardDTO> leaderBoard = new ArrayList<>();

    for (Player player : playerList) {
      if (doesItHaveEnoughGames(gameDifficulty, player)) {
        leaderBoard.add(modelMapper.map(player, LeaderBoardDTO.class));
      }
    }

    return sortedLeaderBoardList(leaderBoard, gameDifficulty);
  }

  private List<LeaderBoardDTO> sortedLeaderBoardList(List<LeaderBoardDTO> list,
      GameDifficulty gameDifficulty) {
    switch (gameDifficulty) {
      case EASY:
        return list.stream().sorted(Comparator.comparing(LeaderBoardDTO::getRankedEasy).reversed())
            .collect(
                Collectors.toList());
      case MEDIUM:
        return list.stream()
            .sorted(Comparator.comparing(LeaderBoardDTO::getRankedMedium).reversed()).collect(
                Collectors.toList());
      case HARD:
        return list.stream().sorted(Comparator.comparing(LeaderBoardDTO::getRankedHard).reversed())
            .collect(
                Collectors.toList());
    }

    return null;
  }

  private void checkUserSignUp(SignUpRequestDTO signUpRequestDTO)
      throws MissingParametersException, UsernameAlreadyTakenException {
    //TODO: MissingParametersException not working properly
    if (signUpRequestDTO == null) {
      SignUpRequestDTO dto = new SignUpRequestDTO();
      throw new MissingParametersException(dto);
    }

    if (signUpRequestDTO.getNullFields().size() > 0) {
      throw new MissingParametersException(signUpRequestDTO);
    }

    Optional<Player> player = playerRepository.findByName(signUpRequestDTO.getUsername());

    if(player.isPresent()) {
      throw new UsernameAlreadyTakenException(signUpRequestDTO.getUsername());
    }
  }

  private Player checkUserSignIn(SignInRequestDTO signInRequestDTO)
      throws MissingParametersException, WrongUsernameOrPasswordException {
    if (signInRequestDTO == null) {
      SignInRequestDTO dto = new SignInRequestDTO();
      throw new MissingParametersException(dto);
    }
    //TODO: create new exception for sign in, duplication in requestDTOs (getNullFields)
    /*if (signInRequestDTO.getNullFields().size() > 0) {
      throw new MissingParametersException(signInRequestDTO);
    }*/

    Optional<Player> player = playerRepository.findByName(signInRequestDTO.getUsername());

    if (!player.isPresent() || !isEncodedPasswordMatches(signInRequestDTO, player.get())) {
      throw new WrongUsernameOrPasswordException();
    }

    return findByName(signInRequestDTO.getUsername());
  }

  private Boolean isEncodedPasswordMatches(SignInRequestDTO dto, Player player) {
    return passwordEncoder().matches(dto.getPassword(), player.getPassword());
  }

  @Override
  public Boolean doesItHaveEnoughGames(GameDifficulty gameDifficulty, Player player) {
    long numberOFGames = player.getGameList().stream()
        .filter(g -> g.getGameType().equals(GameType.RANKED) && g.getDifficulty()
            .equals(gameDifficulty))
        .count();

    return numberOFGames >= 10;
  }

  private PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
