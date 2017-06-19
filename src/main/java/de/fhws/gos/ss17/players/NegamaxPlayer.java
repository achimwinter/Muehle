package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.utils.GameStatus;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.main.Config;
import de.fhws.gos.ss17.players.utils.BoardState;
import de.fhws.gos.ss17.players.utils.Node;
import de.fhws.gos.ss17.players.utils.PossibleMoves;
import java.util.ArrayList;
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
    Move move = getBestMove(board, PositionToken.PLAYER_ONE);
    Node test = negamax(new Node(board,null), depth, 1, MIN_VALUE, MAX_VALUE, System.currentTimeMillis());
    return test.getMove();
  }

  @Override
  protected Move getMovingMove(Board board) throws GameException {
    return null;
  }

  @Override
  protected Move getFlyingMove(Board board) throws GameException {
    return null;
  }

  private Move getBestMove(Board board, PositionToken playerToken) {

    List<Move> validMoves = PossibleMoves.getPossibleMoves(board, super.phase, playerToken);
    int bestResult = Integer.MIN_VALUE;
    Move bestMove = null;

    for (Move move : validMoves) {

      board.executeMove(move, playerToken);
      System.out.println("Evaluating: " + move);

      int evaluationResult = -evaluateNegaMax(this.lookForward, "", Integer.MIN_VALUE, Integer.MAX_VALUE);
      undoMove(move);

      if (evaluationResult > bestResult) {
        bestResult = evaluationResult;
        bestMove = move;
      }
    }
    System.out.println("Done thinking! The best move is: " + bestMove);
    return bestMove;
  }

  public int evaluateNegaMax(int depth, String indent, int alpha, int beta) {
    if (depth <= 0
        || this.chessGame.getGameState() == ChessGame.GAME_STATE_WHITE_WON
        || this.chessGame.getGameState() == ChessGame.GAME_STATE_BLACK_WON) {

      return evaluateState();
    }

    List<Move> moves = generateMoves(false);
    int bestValue = Integer.MIN_VALUE;

    for (Move currentMove : moves) {

      executeMove(currentMove);
      int value = -evaluateNegaMax(depth - 1, indent + "    ", -beta, -alpha);
      System.out.println(indent + "Handling move: " + currentMove + " : " + value);
      undoMove(currentMove);
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
