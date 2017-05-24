package de.fhws.gos.ss17.network;


import com.owlike.genson.annotation.JsonIgnore;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.game.Board;

/**
 * Created by Auron on 18.05.2017.
 */
public class Game {

  private String gameId;
  private String activePlayer;
  private String[] boardState;
  private Long timeStarted;
  private Long timeLastTurnPlayed;
  private String state;
  private Integer turnsTaken;
/*
  public Game(String gameId) {
    this.gameId = gameId;
  }

  public Game(String gameId, String activePlayer, String[] boardState, Integer timeStarted,
      Integer timeLastTurnPlayed, String state, Integer turnsTaken) {

    this.gameId = gameId;
    this.activePlayer = activePlayer;
    this.boardState = boardState;
    this.timeStarted = timeStarted;
    this.timeLastTurnPlayed = timeLastTurnPlayed;
    this.state = state;
    this.turnsTaken = turnsTaken;
  }
*/
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

  public String[] getBoardState() {
    return boardState;
  }

  public Long getTimeStarted() {
    return timeStarted;
  }

  public Long getTimeLastTurnPlayed() {
    return timeLastTurnPlayed;
  }

  public String getState() {
    return state;
  }

  public Integer getTurnsTaken() {
    return turnsTaken;
  }

  @JsonIgnore
  public Board getBoard(String[] boardState){
    Board board = new Board();
    for(int i = 0; i < boardState.length; i++){
      if(boardState[i].equals("NO_TOKEN"))
        board.executeMove(new Move(-1, i , -1), PositionToken.IS_EMPTY);
      else if(boardState[i].equals("6997"))
        board.executeMove(new Move(-1, i,-1), PositionToken.PLAYER_ONE);
      else
        board.executeMove(new Move(-1, i, -1), PositionToken.PLAYER_TWO);
    }
    return board;
  }
}
