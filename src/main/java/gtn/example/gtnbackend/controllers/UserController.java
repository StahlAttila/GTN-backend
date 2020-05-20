package gtn.example.gtnbackend.controllers;

import gtn.example.gtnbackend.domains.dtos.SignInRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignInResponseDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpResponseDTO;
import gtn.example.gtnbackend.security.util.JwtUtil;
import gtn.example.gtnbackend.services.player.PlayerDetailService;
import gtn.example.gtnbackend.services.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private PlayerService playerService;
  private PlayerDetailService playerDetailService;
  private JwtUtil jwtUtil;

  @Autowired
  public UserController(PlayerService playerService, PlayerDetailService playerDetailService,
      JwtUtil jwtUtil) {
    this.playerService = playerService;
    this.playerDetailService = playerDetailService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<?> signUp(@RequestBody SignUpRequestDTO requestDTO) {
    SignUpResponseDTO responseDTO = playerService.signUpPlayer(requestDTO);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<?> signIn(@RequestBody SignInRequestDTO signInRequestDTO) {
    SignInResponseDTO signInResponseDTO = playerService.signInPlayer(signInRequestDTO);

    final UserDetails userDetails = playerDetailService
        .loadUserByUsername(signInRequestDTO.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails, 600);
    signInResponseDTO.setJwt(jwt);

    return new ResponseEntity<>(signInResponseDTO, HttpStatus.OK);
  }
}
