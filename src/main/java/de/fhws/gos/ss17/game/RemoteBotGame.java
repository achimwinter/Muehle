package de.fhws.gos.ss17.game;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Player;
import de.fhws.gos.core.network.Connection;
import de.fhws.gos.core.utils.GameStatus;
import de.fhws.gos.remote.utils.JSONHelper;
import de.fhws.gos.remote.utils.models.ServerEntity;
import de.fhws.gos.ss17.network.DefaultConnection;
import de.fhws.gos.ss17.network.Game;
import de.fhws.gos.ss17.network.JsonConverter;
import java.io.IOException;


/**
 * Created by Achim on 05.05.2017.
 */
public class RemoteBotGame extends AbstractGame {

  private Connection connection;

  public RemoteBotGame(Board board, Connection connection) {
    super(board);
    this.connection = connection;
  }

  public RemoteBotGame(Board board, Player playerOne,
      Player playerTwo) {
    super(board, playerOne, playerTwo);
  }

  public RemoteBotGame(Board board, Player playerOne, Player playerTwo, Connection connection) {
    super(board, playerOne, playerTwo);
    this.connection = connection;
  }

  @Override
  public void startGame() {
    try {
      do {
        System.out.println(DefaultConnection.authorizationToken);
        System.out.println(DefaultConnection.gameId);
        Move playerMove = this.playerOne.getNextMove(board);
        board.executeMove(playerMove, playerOne.getPlayerToken());
        board.printBoard();
        String JsonResponse = connection.playBotgame(JsonConverter.serializeMoveJSON(playerMove));
        Game gameObject1 = JsonConverter.deserializeGameJSON(JsonResponse);
        String[] boardArray = gameObject1.getBoardState();
        board = JsonConverter.deserializeBoard(boardArray);
      } while (this.board.getCurrentGameStatus().equals(GameStatus.RUNNING));


    }catch (GameException ex){
      ex.printStackTrace();
    }
    catch (IOException iox){
      iox.printStackTrace();
    }

  }

  public void initGame() {
    try {
      String response = this.connection.joinBotgame();
      ServerEntity entity = (new JSONHelper()).deserialize(response);
      if (entity.getTurnResult() != null) {
        this.currentPlayer = this.playerTwo;
        this.board.executeMove(entity.getTurnResult().getTurn().asMove(),
            this.currentPlayer.getPlayerToken());
        this.currentPlayer =
            this.currentPlayer.equals(this.playerOne) ? this.playerTwo : this.playerOne;
      } else {
        this.currentPlayer = this.playerOne;
      }

      board.printBoard();
    } catch (IOException var3) {
      System.err.println(var3.getMessage());
    }

  }

}
