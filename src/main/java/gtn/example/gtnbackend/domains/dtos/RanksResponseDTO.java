package gtn.example.gtnbackend.domains.dtos;

public class RanksResponseDTO {

  private String rankedEasy = "Unranked";
  private String rankedMedium = "Unranked";
  private String rankedHard = "Unranked";

  public RanksResponseDTO() {
  }

  public String getRankedEasy() {
    return rankedEasy;
  }

  public void setRankedEasy(String rankedEasy) {
    this.rankedEasy = rankedEasy;
  }

  public String getRankedMedium() {
    return rankedMedium;
  }

  public void setRankedMedium(String rankedMedium) {
    this.rankedMedium = rankedMedium;
  }

  public String getRankedHard() {
    return rankedHard;
  }

  public void setRankedHard(String rankedHard) {
    this.rankedHard = rankedHard;
  }
}
