package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.players.utils.EvaluateMoves;
import de.fhws.gos.ss17.players.utils.LogMove;
import de.fhws.gos.ss17.players.utils.Phase;

/**
 * Created by Achim on 10.05.2017.
 */
public class EvaluatePlayer extends AbstractPlayer {

  public EvaluatePlayer(PositionToken playerToken){
    super(playerToken);
  }

  public Move getNextMove(Board board) throws GameException {
    return super.getNextMove(board);
  }

  @Override
  public Move getMovingMove(Board board) throws GameException{
    Move move = EvaluateMoves.getBestMove(board, Phase.MOVING);
    LogMove.log(move, PositionToken.PLAYER_ONE);
    return move;
  }

  @Override
  public Move getFlyingMove(Board board) throws  GameException{
    Move move = EvaluateMoves.getBestMove(board, Phase.FLYING);
    LogMove.log(move, PositionToken.PLAYER_ONE);
    return EvaluateMoves.getBestMove(board, Phase.FLYING);
  }

  @Override
  public Move getPlacingMove(Board board) throws GameException{
    Move move = EvaluateMoves.getBestMove(board, Phase.PLACING);
    LogMove.log(move, PositionToken.PLAYER_ONE);
    return EvaluateMoves.getBestMove(board, Phase.PLACING);
  }

}
