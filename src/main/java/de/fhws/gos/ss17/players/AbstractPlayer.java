package de.fhws.gos.ss17.players;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.logic.Player;
import de.fhws.gos.ss17.core.utils.GameStatus;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.players.utils.Phase;

/**
 * Created by awinter on 05.04.17.
 */
public abstract class AbstractPlayer implements Player {

  protected Phase phase;
  protected PositionToken playerToken;
  static int counter;

  public AbstractPlayer(PositionToken playerToken) {
    this.playerToken = playerToken;
    this.phase = Phase.PLACING;
  }

  public Move getNextMove(Board board) throws GameException {
    if (counter == 9 && this.phase == Phase.PLACING) {
      this.phase = Phase.MOVING;
    }
    if (board.getNumberOfTokensForPlayer(playerToken) == 3 && this.phase == Phase.MOVING) {
      this.phase = Phase.FLYING;
    }
    if(board.getNumberOfTokensForPlayer(PositionToken.PLAYER_ONE) == 2  && counter > 2){
      board.setCurrentGameStatus(GameStatus.PLAYER_TWO_WON);
    }
    if(board.getNumberOfTokensForPlayer(PositionToken.PLAYER_TWO) == 2  && counter > 2){
      board.setCurrentGameStatus(GameStatus.PLAYER_ONE_WON);
    }
    counter++;
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
