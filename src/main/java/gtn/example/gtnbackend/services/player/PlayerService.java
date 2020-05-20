package gtn.example.gtnbackend.services.player;

import gtn.example.gtnbackend.domains.Player;
import gtn.example.gtnbackend.domains.dtos.SignInRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignInResponseDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {

  Player findByName(String username);

  SignUpResponseDTO signUpPlayer(SignUpRequestDTO signUpDTO);

  SignInResponseDTO signInPlayer(SignInRequestDTO signInRequestDTO);
}
