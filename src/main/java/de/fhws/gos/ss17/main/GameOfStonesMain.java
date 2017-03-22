package de.fhws.gos.ss17.main;

import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Game;
import de.fhws.gos.core.network.Connection;
import de.fhws.gos.game.impl.GameFactory;

/**
 * (c) Tobias Fertig, FHWS 2017
 */
public class GameOfStonesMain {

  public static void main(String[] args) {
    GameOfStonesMain gameOfStonesMain = new GameOfStonesMain();
    gameOfStonesMain.generateGame();
    gameOfStonesMain.showWelcome();
    gameOfStonesMain.startGame();
  }

  private Game game;

  public void generateGame() {
    Connection connection = Config.initConnection();
    Board board = Config.initBoard();

    this.game = GameFactory.getInstance(connection, board).createGame(Config.GAME_MODE);
    this.game.setPlayers(Config.getRandomPlayer(), Config.getRemotePlayer(connection));
  }

  public void showWelcome() {
    System.out.println("Welcome to GameOfStones!");
  }

  public void startGame() {
    game.initGame();
    game.startGame();
  }
}
