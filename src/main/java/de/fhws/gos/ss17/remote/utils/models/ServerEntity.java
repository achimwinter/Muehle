//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.remote.utils.models;

public class ServerEntity {
  private int statusCode;
  private String id;
  private int activePlayer;
  private long creationTime;
  private TurnResult turnResult;

  public ServerEntity() {
  }

  public ServerEntity(int statusCode, String id, int activePlayer, long creationTime, TurnResult turnResult) {
    this.statusCode = statusCode;
    this.id = id;
    this.activePlayer = activePlayer;
    this.creationTime = creationTime;
    this.turnResult = turnResult;
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getActivePlayer() {
    return this.activePlayer;
  }

  public void setActivePlayer(int activePlayer) {
    this.activePlayer = activePlayer;
  }

  public long getCreationTime() {
    return this.creationTime;
  }

  public void setCreationTime(long creationTime) {
    this.creationTime = creationTime;
  }

  public TurnResult getTurnResult() {
    return this.turnResult;
  }

  public void setTurnResult(TurnResult turnResult) {
    this.turnResult = turnResult;
  }
}
