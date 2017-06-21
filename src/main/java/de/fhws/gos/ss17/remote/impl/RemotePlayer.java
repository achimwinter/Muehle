//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.remote.impl;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.logic.Player;
import de.fhws.gos.ss17.core.network.Connection;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.remote.exceptions.GameLostException;
import de.fhws.gos.ss17.remote.exceptions.GameTieException;
import de.fhws.gos.ss17.remote.exceptions.GameWonException;
import de.fhws.gos.ss17.remote.utils.GameResponse;
import de.fhws.gos.ss17.remote.utils.JSONHelper;
import de.fhws.gos.ss17.remote.utils.Phase;
import de.fhws.gos.ss17.remote.utils.models.ServerEntity;
import de.fhws.gos.ss17.remote.utils.models.Turn;
import java.io.IOException;

public abstract class RemotePlayer implements Player {
  private PositionToken playerToken;
  private Phase phase;
  protected JSONHelper jsonHelper;
  protected Connection connection;

  public RemotePlayer(PositionToken playerToken, Connection connection) {
    this.playerToken = playerToken;
    this.phase = Phase.PLACING;
    this.connection = connection;
    this.jsonHelper = new JSONHelper();
  }

  public Move getNextMove(Board board) throws GameException {
    if(board.getNumberOfTokensForPlayer(this.playerToken) == 3 && this.phase.equals(Phase.MOVING)) {
      this.phase = Phase.FLYING;
    }

    if(board.getNumberOfMovesForPlayer(this.playerToken) == 9 && this.phase.equals(Phase.PLACING)) {
      this.phase = Phase.MOVING;
    }

    Move nextMove = this.doGetNextMove(board);
    return nextMove;
  }

  public Move doGetNextMove(Board board) throws GameException {
    Turn turn = new Turn(board.getLastMove());

    try {
      String response = this.doPlayGame(turn);
      ServerEntity entity = this.jsonHelper.deserialize(response);
      if(GameResponse.getGameResponseForStatus(entity.getStatusCode()) != GameResponse.NEXT_TURN) {
        this.checkResponse(entity.getStatusCode());
      }

      return entity.getTurnResult().getTurn().asMove();
    } catch (IOException var5) {
      throw new GameException(var5.getMessage());
    }
  }

  protected abstract String doPlayGame(Turn var1) throws IOException;

  private void checkResponse(int statusCode) throws GameException {
    switch (GameResponse.getGameResponseForStatus(statusCode).ordinal()){
      case 1:
      case 2:
      case 3:
        throw new GameWonException();
      case 4:
      case 5:
        throw new GameLostException();
      case 6:
        throw new GameTieException();
      default:
    }
  }

  public boolean canFly() {
    return this.phase.equals(Phase.FLYING);
  }

  public PositionToken getPlayerToken() {
    return this.playerToken;
  }
}
