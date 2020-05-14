package gtn.example.gtnbackend.controllers;

import gtn.example.gtnbackend.domains.dtos.SignUpRequestDTO;
import gtn.example.gtnbackend.domains.dtos.SignUpResponseDTO;
import gtn.example.gtnbackend.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private PlayerService playerService;

  @Autowired
  public UserController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<?> signUp(@RequestBody SignUpRequestDTO requestDTO) {
    SignUpResponseDTO responseDTO = playerService.signUpPlayer(requestDTO);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }
}
