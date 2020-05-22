package gtn.example.gtnbackend.domains.dtos;

public class LeaderBoardDTO {

  private String name;
  private Integer rankedEasy;
  private Integer rankedMedium;
  private Integer rankedHard;

  public LeaderBoardDTO() {
  }

  public LeaderBoardDTO(String name, Integer rankedEasy, Integer rankedMedium,
      Integer rankedHard) {
    this.name = name;
    this.rankedEasy = rankedEasy;
    this.rankedMedium = rankedMedium;
    this.rankedHard = rankedHard;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getRankedEasy() {
    return rankedEasy;
  }

  public void setRankedEasy(Integer rankedEasy) {
    this.rankedEasy = rankedEasy;
  }

  public Integer getRankedMedium() {
    return rankedMedium;
  }

  public void setRankedMedium(Integer rankedMedium) {
    this.rankedMedium = rankedMedium;
  }

  public Integer getRankedHard() {
    return rankedHard;
  }

  public void setRankedHard(Integer rankedHard) {
    this.rankedHard = rankedHard;
  }
}
