package de.fhws.gos.ss17.game;

import de.fhws.gos.core.logic.*;
import de.fhws.gos.core.network.Connection;

/**
 * Created by Achim on 05.05.2017.
 */
public class GameFactory {

  private static GameFactory instance;
  private Connection connection;
  private BoardImpl board;

  public static GameFactory getInstance(Connection connection, BoardImpl board) {
    if (instance == null) {
      instance = new GameFactory(connection, board);
    }

    return instance;
  }

  private GameFactory(Connection connection, BoardImpl board) {
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
      return new RemoteBotGame(board, connection);
  }


}
