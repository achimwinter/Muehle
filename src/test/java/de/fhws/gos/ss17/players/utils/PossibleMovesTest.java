package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.main.Config;
import java.util.List;
import org.junit.Test;

/**
 * Created by awinter on 09.05.17.
 */
public class PossibleMovesTest {

  private Board board = Config.initBoard();
  private PositionToken playerToken = PositionToken.PLAYER_ONE;


  @Test
  public void testGetMoves_2MovesGotten_MovesPrinted() throws GameException{
    board.executeMove((new Move(-1, 0, -1)), playerToken);
    List<Move> moves=  PossibleMoves.possibleMoves(board, Phase.MOVING);
    for(Move move: moves){
      System.out.println("From: " + move.getFromId() + " TO: " + move.getToId() + " Remove " + move.getRemoveId());
    }

  }
}


