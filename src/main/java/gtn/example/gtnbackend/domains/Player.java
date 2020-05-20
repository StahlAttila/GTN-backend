package gtn.example.gtnbackend.domains;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Player {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String email;
  private String password;
  private Integer rankScore;

  @OneToMany
  private List<Game> gameList;

  public Player() {
  }

  public Player(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.rankScore = 0;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getRankScore() {
    return rankScore;
  }

  public void setRankScore(Integer rankScore) {
    this.rankScore = rankScore;
  }

  public List<Game> getGameList() {
    return gameList;
  }

  public void setGameList(List<Game> gameList) {
    this.gameList = gameList;
  }
}
