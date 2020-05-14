package gtn.example.gtnbackend.repositories;

import gtn.example.gtnbackend.domains.Player;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
  Optional<Player> findByName(String username);
}
