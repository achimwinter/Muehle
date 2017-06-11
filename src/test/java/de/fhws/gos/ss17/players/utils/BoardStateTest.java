package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.main.Config;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Achim on 11.06.2017.
 */
public class BoardStateTest {

  Board board = Config.initBoard();
  PositionToken playerToken = PositionToken.PLAYER_ONE;
  PositionToken enemy = PositionToken.PLAYER_TWO;

  @Test
  public void testGetDoubleMills_1DoubleMill_TrueReturned()throws GameException{
    board.getPosition(0).setPositionToken(playerToken);
    board.getPosition(9).setPositionToken(playerToken);
    board.getPosition(21).setPositionToken(playerToken);
    board.getPosition(22).setPositionToken(playerToken);
    board.getPosition(23).setPositionToken(playerToken);
    board.printBoard();
    int numberOfDoubleMills = BoardState.getDoubleMills(board, playerToken);
    boolean result = false;
    if (numberOfDoubleMills == 1)
      result = true;
    Assert.assertTrue("No Double Mill found", result);

  }


}
