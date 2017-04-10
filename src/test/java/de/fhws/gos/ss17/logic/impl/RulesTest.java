package de.fhws.gos.ss17.logic.impl;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.main.Config;
import de.fhws.gos.ss17.players.MillCombinations;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Achim on 30.03.2017.
 */

public class RulesTest {

  private Board board = Config.initBoard();
  private PositionToken playerToken = PositionToken.PLAYER_ONE;

  RulesImpl test = new RulesImpl();


  @Test
  public void testisMill_MillClosed_TrueReturned() throws GameException {
    board.executeMove((new Move(-1, 0, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.printBoard();
    boolean result = test.isMill(board, playerToken, 1);
    Assert.assertTrue("was mill, returned false", result);
  }

  @Test
  public void testwillBeMill_MillClosed_TrueReturned() throws GameException {
    board.executeMove((new Move(-1, 9, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.printBoard();
    boolean result = test.willBeMill(board, playerToken, 9, 0);
    Assert.assertTrue("Move wouldn´t close a mill, returned false", result);
  }

  @Test
  public void testisValidFrom_stonefromplayerisplaced_TrueReturned() throws GameException {
    board.executeMove((new Move(-1, 9, -1)), playerToken);
    board.printBoard();
    boolean result = test.isValidFrom(board, playerToken, 9);
    Assert.assertTrue("stone can´t be placed there, returned false", result);
  }

  @Test
  public void testisValidTo_PositionNotOccupied_TrueReturned() throws GameException {
    board.executeMove((new Move(-1, 9, -1)), playerToken);
    board.printBoard();
    boolean result = test.isValidTo(board, 8);
    Assert.assertTrue("ToId is not available, returned false", result);
  }

  @Test
  public void testisValidRemove_StoneCanBeRemoved_TrueReturned() throws GameException {
    board.executeMove((new Move(-1, 9, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 0, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 21, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 13, -1)), PositionToken.PLAYER_TWO);
    board.printBoard();
    boolean result = test.isValidRemove(board, playerToken, 13);
    Assert.assertTrue("stone can´t be removed, returned false", result);
  }

  //Checken ob nurnoch 3 Steine?
  @Test
  public void testisValidMove_StoneCanBeMoved_TrueReturned() throws GameException {
    board.executeMove((new Move(-1, 9, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.printBoard();
    boolean result = test.isValidMove(board, playerToken, 9, 21);
    Assert.assertTrue("stone can´t be moved this way, returned false", result);
  }

  @Test
  public void testisValidFyling_StoneCanFly_TrueReturned() throws GameException {
    board.executeMove((new Move(-1, 23, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.printBoard();
    boolean result = test.isValidFlying(board, playerToken, 23, 0);
    Assert.assertTrue("Flying is not available, returned false", result);

  }


}
