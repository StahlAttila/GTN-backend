package gtn.example.gtnbackend.services;

import gtn.example.gtnbackend.domains.Player;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlayerDetailService implements UserDetailsService {

  private PlayerService playerService;

  @Autowired
  public PlayerDetailService(PlayerService playerService) {
    this.playerService = playerService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Player player = playerService.findByName(username);
    return new User(username, player.getPassword(), new ArrayList<>());
  }
}
