package de.fhws.gos.ss17.players;


import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.players.utils.LogMove;

/**
 * Created by Neuer on 22.04.2017.
 */
public class ShortLogPlayer extends AdvancedRandomPlayer {

  public ShortLogPlayer(PositionToken playerToken) {
    super(playerToken);
  }

  protected Move getMovingMove(Board board) throws GameException {
    Move enemymove = board.getLastMove();
    LogMove.log(enemymove, PositionToken.PLAYER_TWO);
    Move move = super.getMovingMove(board);
    LogMove.log(move, playerToken);
    return super.getMovingMove(board);
  }

  protected Move getPlacingMove(Board board) throws GameException {
    try {
      Move enemymove = board.getLastMove();
      LogMove.log(enemymove, PositionToken.PLAYER_TWO);
    } catch (Exception ex) {
    }

    Move move = super.getPlacingMove(board);
    LogMove.log(move, playerToken);
    return move;
  }

  protected Move getFlyingMove(Board board) throws GameException {
    Move enemymove = board.getLastMove();
    LogMove.log(enemymove, PositionToken.PLAYER_TWO);
    Move move = super.getFlyingMove(board);
    LogMove.log(move, playerToken);
    return move;
  }
}
