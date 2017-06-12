package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.ss17.game.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.players.utils.BoardState;
import de.fhws.gos.ss17.players.utils.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Achim on 08.06.2017.
 */
public class NegamaxPlayer extends AbstractPlayer{

  //generate possible Moves
  //getBoardScore
  //Copy Board
  private static final long timePerRound = 28 * 1000;

  public NegamaxPlayer(PositionToken playerToken){
    super(playerToken);
  }

  @Override
  protected Move getPlacingMove(de.fhws.gos.core.logic.Board var1) throws GameException {
    return null;
  }

  @Override
  protected Move getMovingMove(de.fhws.gos.core.logic.Board var1) throws GameException {
    return null;
  }

  @Override
  protected Move getFlyingMove(de.fhws.gos.core.logic.Board var1) throws GameException {
    return null;
  }


  public Node negamax(Node node, int depth, int alpha, int beta, int playerValue){
    Node bestNode;
    long startTime = System.currentTimeMillis();
    if(depth == 0 || (timePerRound + startTime == System.currentTimeMillis()))
      return bestNode;
    List<Move> nodeMoves= new ArrayList<>();
    Iterator<Move> moveIterator = nodeMoves.iterator();
    while(moveIterator.hasNext()){
      Move move = moveIterator.next();
      Board childBoard = new Board(node.board);
      PositionToken playerToken = (playerValue == 1) ? PositionToken.PLAYER_ONE : PositionToken.PLAYER_TWO;
      childBoard.executeMove(move, playerToken);
      node.boardValue = BoardState.getScore(node.board);
    }
  }

}
