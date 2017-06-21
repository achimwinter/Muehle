package de.fhws.gos.ss17.network;



/**
 * Created by Auron on 18.05.2017.
 */
public class Game {

  private String gameId;
  private String activePlayer;
  private String[] boardState;
  private Long timeStarted;
  private Long timeLastTurnPlayed;
  private GameState state;
  private Integer turnsTaken;

  public Game() {
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public void setActivePlayer(String activePlayer) {
    this.activePlayer = activePlayer;
  }

  public void setBoardState(String[] boardState) {
    this.boardState = boardState;
  }

  public void setTimeStarted(Long timeStarted) {
    this.timeStarted = timeStarted;
  }

  public void setTimeLastTurnPlayed(Long timeLastTurnPlayed) {
    this.timeLastTurnPlayed = timeLastTurnPlayed;
  }

  public void setState(GameState state) {
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

  public String[] getBoardState() {
    return boardState;
  }

  public Long getTimeStarted() {
    return timeStarted;
  }

  public Long getTimeLastTurnPlayed() {
    return timeLastTurnPlayed;
  }

  public GameState getState() {
    return state;
  }

  public Integer getTurnsTaken() {
    return turnsTaken;
  }

}
