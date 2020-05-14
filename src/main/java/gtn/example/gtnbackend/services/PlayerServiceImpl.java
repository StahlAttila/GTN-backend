package gtn.example.gtnbackend.services;

import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.SignInRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignInResponseDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpResponseDTO;
import gtn.example.gtnbackend.repositories.PlayerRepository;
import gtn.example.gtnbackend.security.util.JwtUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

  private PlayerRepository playerRepository;

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
  public SignUpResponseDTO signUpPlayer(SignUpRequestDTO signUpRequestDTO) {
    checkUserSignUp(signUpRequestDTO);
    Player player = new Player(signUpRequestDTO.getUsername(), signUpRequestDTO.getEmail(),
        passwordEncoder().encode(signUpRequestDTO.getPassword()));
    playerRepository.save(player);
    return new SignUpResponseDTO(player.getId(), player.getName(), player.getEmail());
  }

  @Override
  public SignInResponseDTO signInPlayer(SignInRequestDTO signInRequestDTO) {
    Player player = checkUserSignIn(signInRequestDTO);

    return new SignInResponseDTO(player.getName(), null);
  }

  private void checkUserSignUp(SignUpRequestDTO signUpRequestDTO) {
    //TODO: implement register check with custom exceptions
  }

  private Player checkUserSignIn(SignInRequestDTO signInRequestDTO) {
    //TODO: implement login check with custom exceptions

    return findByName(signInRequestDTO.getUsername());
  }

  private PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
