package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.main.Config;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Achim on 09.04.2017.
 */
public class MillCombinationsTest {

  private Board board = Config.initBoard();
  private PositionToken playerToken = PositionToken.PLAYER_ONE;

  @Test
  public void testWillBeMill_Only2StonesinMillPos_FalseReturned() throws GameException {
    board.executeMove((new Move(-1, 0, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 21, -1)), PositionToken.PLAYER_TWO);
    boolean result = (MillCombinations.getInstance(board).willBeMill(playerToken, 21, 9));
    board.printBoard();
    Assert.assertFalse("Mill found, returned true", result);
  }


  @Test
  public void testAllinMill_NoStonesPlaced_FalseReturned() throws GameException {
    board.executeMove((new Move(-1, 0, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 1, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 2, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 21, -1)), PositionToken.PLAYER_TWO);
    board.printBoard();
    boolean result = MillCombinations.getInstance(board).allInMill(playerToken);
    Assert.assertFalse("Not all Stones are in Mill", result);
  }


  @Test
  public void testAllInMill_AllStonesInMill_TrueReturned() throws GameException {
    System.out.println("preallinmistest");
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
  public void testIsMill_StoneIsPartOfMill_TrueReturned() throws GameException {
    board.executeMove((new Move(-1, 0, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.printBoard();
    boolean result = MillCombinations.getInstance(board).isMill(playerToken, -1, 1);
    Assert.assertTrue("Stone was not in Mill, returned false", result);
  }

  @Before
  public void setBoard()throws GameException {

    for (int i = 0; i < 23; i++) {
      board.getPosition(i).setPositionToken(PositionToken.IS_EMPTY);
    }

    //this.board = Config.initBoard();
  }

  @Test
  public void testIsMill_NoMillDetected_FalseReturned() throws GameException {
    board.executeMove((new Move(-1, 3, -1)), playerToken);
    board.executeMove((new Move(-1, 9, -1)), playerToken);
    board.executeMove((new Move(-1, 0, -1)), playerToken);
    board.executeMove((new Move(-1, 21, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 7, -1)), playerToken);
    board.executeMove((new Move(-1, 4, -1)), playerToken);
    board.executeMove((new Move(-1, 18, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 23, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 16, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 12, -1)), PositionToken.PLAYER_TWO);
    boolean result = MillCombinations.getInstance(board).willBeMill(playerToken, -1, 10);
    board.printBoard();
    System.out.println(result);
    Assert.assertFalse("Wasn´t mill but true returned", result);
  }


  //Hier ist ein Fehler, erzeugt RemoveId obwohl keine erzeugt werden sollte.
  @Test
  public void testisMill_NoMill_FalseReturned() throws GameException {
    setBoard();
    board.executeMove((new Move(-1, 0, -1)), playerToken);
    board.executeMove((new Move(-1, 1, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 3, -1)), playerToken);
    board.executeMove((new Move(-1, 7, -1)), playerToken);
    board.executeMove((new Move(-1, 10, -1)), playerToken);
    board.executeMove((new Move(-1, 11, -1)), playerToken);
    board.executeMove((new Move(-1, 14, -1)), playerToken);
    board.executeMove((new Move(-1, 8, -1)), playerToken);
    board.executeMove((new Move(-1, 12, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 15, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 18, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 5, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 13, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 21, -1)), PositionToken.PLAYER_TWO);
    boolean result = MillCombinations.getInstance(board).willBeMill(playerToken, 1, 4);
    Move move;
    move = EvaluateMoves.getBestMove(board, Phase.MOVING);
    System.out.println(
        "From: " + move.getFromId() + " TO: " + move.getToId() + " Remove " + move.getRemoveId());
    board.printBoard();
    System.out.println(result);
    Assert.assertFalse("Wasn´t mill but true returned", result);
  }

}


