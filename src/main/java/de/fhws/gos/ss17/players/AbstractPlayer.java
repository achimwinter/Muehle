package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Player;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.players.utils.Phase;

/**
 * Created by awinter on 05.04.17.
 */
public abstract class AbstractPlayer implements Player {

  protected Phase phase;
  protected PositionToken playerToken;

  public AbstractPlayer(PositionToken playerToken) {
    this.playerToken = playerToken;
    this.phase = Phase.PLACING;
  }

  public Move getNextMove(Board board) throws GameException {
    if (board.getNumberOfMovesForPlayer(playerToken) == 9 && this.phase == Phase.PLACING) {
      this.phase = Phase.MOVING;
    }
    if (board.getNumberOfTokensForPlayer(playerToken) == 3 && this.phase == Phase.MOVING) {
      this.phase = Phase.FLYING;
    }
    Move nextMove = this.doGetNextMove(board);
    return nextMove;
  }

  protected Move doGetNextMove(Board board) throws GameException {
    Move move = new Move();
    if (this.phase == Phase.PLACING) {
      move = getPlacingMove(board);
    }
    if (this.phase == Phase.MOVING) {
      move = getMovingMove(board);
    }
    if (this.phase == Phase.FLYING) {
      move = getFlyingMove(board);
    }
    return move;
  }

  protected abstract Move getPlacingMove(Board var1) throws GameException;

  protected abstract Move getMovingMove(Board var1) throws GameException;

  protected abstract Move getFlyingMove(Board var1) throws GameException;

  public boolean canFly() {
    return this.phase.equals(Phase.FLYING);
  }

  public PositionToken getPlayerToken() {
    return this.playerToken;
  }
}
