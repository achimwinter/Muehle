package de.fhws.gos.ss17.main;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Game;
import de.fhws.gos.core.network.Connection;
import de.fhws.gos.ss17.game.Board;
import de.fhws.gos.ss17.game.GameFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * (c) Tobias Fertig, FHWS 2017
 */
public class GameOfStonesMain {

  public static void main(String[] args) throws GameException {
    GameOfStonesMain gameOfStonesMain = new GameOfStonesMain();
    gameOfStonesMain.generateGame();
    gameOfStonesMain.showWelcome();
    gameOfStonesMain.createLog();
    gameOfStonesMain.startGame();
  }
  public static String logName;
  private Game game;

  public void generateGame() throws GameException {
    Connection connection = Config.initConnection();
    Board board = Config.initBoard();

    this.game = GameFactory.getInstance(connection, board).createGame(Config.GAME_MODE);
    this.game.setPlayers(Config.getEvaluatePlayer(), Config.getRemotePlayer(connection));
  }

  public void showWelcome() {
    System.out.println("Welcome to GameOfStones!");
  }

  public void startGame() {
    game.initGame();
    game.startGame();
  }
  public String createLog() {
    DateFormat dateFormat = new SimpleDateFormat("MM_dd_HH_mm_ss");
    Calendar cal = Calendar.getInstance();
    this.logName = "logs/" + dateFormat.format(cal.getTime()) + ".log";
    return this.logName;
  }
}
