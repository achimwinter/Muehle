package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.main.Config;
import org.junit.Test;

/**
 * Created by awinter on 16.05.17.
 */
public class GetRemoveIdTest {

  private Board board = Config.initBoard();
  private PositionToken playerToken = PositionToken.PLAYER_ONE;

  @Test
  public void testGetRemoveId_NoNeighbors_Return1() throws GameException {
    board.executeMove((new Move(-1, 0, -1)), playerToken);
    board.executeMove((new Move(-1, 21, -1)), playerToken);
    board.executeMove((new Move(-1, 10, -1)), playerToken);
    board.executeMove((new Move(-1, 13, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 12, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 14, -1)), PositionToken.PLAYER_TWO);
    //board.executeMove((new Move(-1, 23, -1)), PositionToken.PLAYER_TWO);
    Move move = EvaluateMoves.getBestMove(board, Phase.MOVING);
    board.printBoard();
    System.out.println(
        "From: " + move.getFromId() + " TO: " + move.getToId() + " Remove " + move.getRemoveId());


  }

}
