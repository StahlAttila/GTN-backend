package gtn.example.gtnbackend.services;

import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.SignUpRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpResponseDTO;
import gtn.example.gtnbackend.repositories.PlayerRepository;
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
  public SignUpResponseDTO signUpPlayer(SignUpRequestDTO signUpDTO) {
    checkUserSignUp(signUpDTO);
    Player player = new Player(signUpDTO.getUsername(), signUpDTO.getEmail(),
        passwordEncoder().encode(signUpDTO.getPassword()));
    playerRepository.save(player);
    return new SignUpResponseDTO(player.getId(), player.getName(), player.getEmail());
  }

  private void checkUserSignUp(SignUpRequestDTO signUpDTO) {
    //TODO: implement register check with custom exceptions
  }

  private PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
