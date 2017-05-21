package de.fhws.gos.ss17.network;

/**
 * Created by Auron on 18.05.2017.
 */
public class Game {

  private String gameId;
  private String activePlayer;
  private String boardState;
  private Integer timeStarted;
  private Integer timeLastTurnPlayed;
  private String state;
  private Integer turnsTaken;

  public Game(String gameId) {
    this.gameId = gameId;
  }

  public Game(String gameId, String activePlayer, String boardState, Integer timeStarted,
      Integer timeLastTurnPlayed, String state, Integer turnsTaken) {

    this.gameId = gameId;
    this.activePlayer = activePlayer;
    this.boardState = boardState;
    this.timeStarted = timeStarted;
    this.timeLastTurnPlayed = timeLastTurnPlayed;
    this.state = state;
    this.turnsTaken = turnsTaken;
  }

  public void setGameId(String gameId) {

    this.gameId = gameId;
  }

  public void setActivePlayer(String activePlayer) {
    this.activePlayer = activePlayer;
  }

  public void setBoardState(String boardState) {
    this.boardState = boardState;
  }

  public void setTimeStarted(Integer timeStarted) {
    this.timeStarted = timeStarted;
  }

  public void setTimeLastTurnPlayed(Integer timeLastTurnPlayed) {
    this.timeLastTurnPlayed = timeLastTurnPlayed;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setTurnsTaken(Integer turnsTaken) {
    this.turnsTaken = turnsTaken;
  }

  public String getGameId() {

    return gameId;
  }

  public String getActivePlayer() {
    return activePlayer;
  }

  public String getBoardState() {
    return boardState;
  }

  public Integer getTimeStarted() {
    return timeStarted;
  }

  public Integer getTimeLastTurnPlayed() {
    return timeLastTurnPlayed;
  }

  public String getState() {
    return state;
  }

  public Integer getTurnsTaken() {
    return turnsTaken;
  }
}