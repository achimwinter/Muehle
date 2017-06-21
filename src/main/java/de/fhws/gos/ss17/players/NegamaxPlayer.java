package de.fhws.gos.ss17.players;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.utils.GameStatus;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.players.utils.BoardState;
import de.fhws.gos.ss17.players.utils.Node;
import de.fhws.gos.ss17.players.utils.PossibleMoves;
import java.util.List;

/**
 * Created by Achim on 08.06.2017.
 */
public class NegamaxPlayer extends AbstractPlayer{

  //generate possible Moves
  //getBoardScore
  //Copy Board
  private final static int MAX_VALUE = 9999;
  private final static int MIN_VALUE = -9999;
  private static final long timePerRound = 28 * 1000;
  private static final int depth = 3;

  public NegamaxPlayer(PositionToken playerToken){
    super(playerToken);
  }

  @Override
  protected Move getPlacingMove(Board board) throws GameException {
    return getBestMove(board, playerToken);
  }

  @Override
  protected Move getMovingMove(Board board) throws GameException {
    return getBestMove(board, playerToken);
  }

  @Override
  protected Move getFlyingMove(Board board) throws GameException {
    return getBestMove(board, playerToken);
  }

  private Move getBestMove(Board board, PositionToken playerToken) {
    int playerValue =
        (playerToken.equals(PositionToken.PLAYER_ONE)) ? 1: -1;
    List<Move> validMoves = PossibleMoves.getPossibleMoves(board, super.phase, playerToken);
    int bestResult = Integer.MIN_VALUE;
    Move bestMove = null;

    for (Move move : validMoves) {

      board.executeMove(move, playerToken);
      System.out.println("Evaluating: " + move);

      int evaluationResult = -evaluateNegaMax(depth - 1, "", Integer.MIN_VALUE, Integer.MAX_VALUE,
          board, playerValue);

      try {
        board.undoMove(move);
      } catch (GameException e) {
        e.printStackTrace();
      }

      if (evaluationResult > bestResult) {
        bestResult = evaluationResult;
        bestMove = move;
      }
    }
    return bestMove;
  }

  public int evaluateNegaMax(int depth, String indent, int alpha, int beta, Board board, int playerValue) {
    PositionToken playerToken =
        (playerValue == 1) ? PositionToken.PLAYER_ONE : PositionToken.PLAYER_TWO;
    if (depth <= 0
        || !board.getCurrentGameStatus().equals(GameStatus.RUNNING)) {

      return BoardState.getScore(board, playerValue, super.phase);
    }

    List<Move> moves = PossibleMoves.getPossibleMoves(board, super.phase, playerToken);
    int bestValue = Integer.MIN_VALUE;

    for (Move currentMove : moves) {

      board.executeMove(currentMove, playerToken);
      int value = -evaluateNegaMax(depth - 1, indent + "    ", -beta, -alpha, board, -playerValue);
      //System.out.println(indent + "Handling move: " + currentMove + " : " + value);

      try {
        board.undoMove(currentMove);
      } catch (GameException e) {
        e.printStackTrace();
      }

      counter++;

      if (value > bestValue) {
        bestValue = value;
      }

      if (bestValue > alpha) {
        alpha = bestValue;
      }

      if (bestValue >= beta) {
        break;
      }
    }
    System.out.println(indent + "max: " + alpha);
    return alpha;
  }
}
