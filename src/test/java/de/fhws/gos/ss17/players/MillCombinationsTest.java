package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Player;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.main.Config;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Achim on 09.04.2017.
 */
public class MillCombinationsTest {

  private Board board = Config.initBoard();
  private PositionToken playerToken = PositionToken.PLAYER_ONE;

  @Test
  public void testWillBeMill() throws GameException{
    board.executeMove((new Move(-1, 3, -1)), playerToken);
    board.executeMove((new Move(-1, 18, -1)), playerToken);
    //board.executeMove((new Move(-1, 9, -1)), playerToken);
    Integer[][] comb = MillCombinations.getInstance(board).getMillCombinations(9);
    System.out.println(comb[1][0] + " "  + comb[1][1] + " " + comb[1][2]);
    System.out.println(comb[0][0] + " "  + comb[0][1] + " " + comb[0][2]);
    System.out.println(MillCombinations.getInstance(board).willBeMill(playerToken, 18,10));
  }


  @Test
  public void testAllinMill_NoStonesPlaced_FalseReturned() throws GameException{
    board.printBoard();
    boolean result = MillCombinations.getInstance(board).allInMill(playerToken);
    Assert.assertFalse("Not all Stones are in Mill", result);
  }


  @Test
  public void testAllInMill_AllStonesInMill_TrueReturned() throws GameException {
    board.executeMove((new Move(-1, 0, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.executeMove((new Move(-1, 9, -1)), playerToken);
    board.executeMove((new Move(-1, 21, -1)), playerToken);
    board.printBoard();
    boolean result = MillCombinations.getInstance(board).allInMill(playerToken);
    Assert.assertTrue("Stones remaining which are not in Mill, returned false", result);
  }

  @Test
  public void testAllInMill_AllStonesInMill_FalseReturned() throws GameException {
    board.executeMove((new Move(-1, 0, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.executeMove((new Move(-1, 4, -1)), playerToken);
    board.printBoard();
    boolean result = MillCombinations.getInstance(board).allInMill(playerToken);
    Assert.assertFalse("All Stones are in Mill, returned true", result);
  }

  @Test
  public void testIsMill_StoneIsPartOfMill_TrueReturned() throws GameException{
    board.executeMove((new Move(-1, 0, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.printBoard();
    boolean result = MillCombinations.getInstance(board).isMill(playerToken,-1, 1);
    Assert.assertTrue("Stone was not in Mill, returned false", result);
  }






}


