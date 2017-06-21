package de.fhws.gos.ss17.game;

import de.fhws.gos.ss17.core.logic.Game;
import de.fhws.gos.ss17.core.network.Connection;
import de.fhws.gos.ss17.core.logic.Board;

/**
 * Created by Achim on 05.05.2017.
 */
public class GameFactory{

  private static GameFactory instance;
  private Connection connection;
  private Board board;

  public static GameFactory getInstance(Connection connection, Board board) {
    if (instance == null) {
      instance = new GameFactory(connection, board);
    }

    return instance;
  }

  private GameFactory(Connection connection, Board board) {
    this.connection = connection;
    this.board = board;
  }

  public Game getGame(Game gameMode) {
    if (gameMode == null) {
      return null;
    } else {
      return gameMode;
    }
  }

  public Game createGame(String gameMode) {
    if(gameMode.equals("remoteversusgame"))
      return new RemoteVersusGame(board, connection);
    else
      return new RemoteBotGame(board, connection);
  }
}
