package de.fhws.gos.ss17.game;


import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Player;
import de.fhws.gos.core.utils.GameStatus;
import de.fhws.gos.remote.exceptions.GameLostException;
import de.fhws.gos.remote.exceptions.GameTieException;
import de.fhws.gos.remote.exceptions.GameWonException;
import de.fhws.gos.ss17.exceptions.CheckedExceptions;
import de.fhws.gos.ss17.network.DefaultConnection;

/**
 * Created by awinter on 05.05.17.
 */
public abstract class AbstractGame implements de.fhws.gos.core.logic.Game {


  protected de.fhws.gos.core.logic.Board board;
  protected Player playerOne;
  protected Player playerTwo;
  protected Player currentPlayer;

  public AbstractGame(Board board) {
    this.board = board;
  }

  public AbstractGame(Board board, Player playerOne, Player playerTwo) {
    this.board = board;
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
  }

  public void initGame() {
  }

  public void startGame() {

  }


  public void printGameResult() {
    GameStatus status = board.getCurrentGameStatus();
    if (status.equals(GameStatus.ERROR)) {
      System.out.println("Error while Game");
    } else if (status.equals(GameStatus.PLAYER_ONE_WON)) {
      System.out.println("Congrats Player One");
    } else if (status.equals(GameStatus.PLAYER_TWO_WON)) {
      System.out.println("Congrats Player Two");
    } else if (status.equals(GameStatus.TIE)) {
      System.out.println("The Game was a tie");
    }
  }


  public void setPlayers(Player playerOne, Player playerTwo) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
  }
}
