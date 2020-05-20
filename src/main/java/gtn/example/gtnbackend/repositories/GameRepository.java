package gtn.example.gtnbackend.repositories;

import gtn.example.gtnbackend.domains.Game;
import gtn.example.gtnbackend.domains.Player;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

  List<Game> findAllByPlayer(Player player);
}
