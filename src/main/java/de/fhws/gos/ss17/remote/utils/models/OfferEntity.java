package de.fhws.gos.ss17.remote.utils.models;

public class OfferEntity {
  private String status;
  private String gameId;

  public OfferEntity(String status, String gameId) {
    this.status = status;
    this.gameId = gameId;
  }

  public OfferEntity() {
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getGameId() {
    return this.gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }
}
