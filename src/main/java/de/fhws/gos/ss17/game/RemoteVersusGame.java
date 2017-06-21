package de.fhws.gos.ss17.game;

import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Player;
import de.fhws.gos.ss17.core.network.Connection;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.remote.utils.JSONHelper;
import de.fhws.gos.ss17.remote.utils.models.ServerEntity;
import java.io.IOException;

/**
 * Created by Achim on 16.05.2017.
 */
public class RemoteVersusGame extends AbstractGame {

  private Connection connection;

  public RemoteVersusGame(Board board, Connection connection) {
    super(board);
    this.connection = connection;
  }

  public RemoteVersusGame(Board board, Player playerOne,
      Player playerTwo, Connection connection) {
    super(board, playerOne, playerTwo);
    this.connection = connection;
  }

  public void initGame(){
    try {
      String response = this.connection.joinGame();
      ServerEntity entity = (new JSONHelper()).deserialize(response);
      this.connection.setGameId(entity.getId());
      if (entity.getTurnResult() != null) {
        this.currentPlayer = this.playerTwo;
        this.board.executeMove(entity.getTurnResult().getTurn().asMove(),
            this.currentPlayer.getPlayerToken());
        if(this.currentPlayer.equals(PositionToken.PLAYER_ONE)){
          this.currentPlayer = playerTwo;
        }else{
          this.currentPlayer = playerOne;
        }
      } else {
        this.currentPlayer = this.playerOne;
      }
    }catch (IOException ex){
      ex.printStackTrace();
    }
  }
}