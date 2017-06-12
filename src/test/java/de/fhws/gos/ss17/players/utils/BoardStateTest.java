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

  @Test
  public void testGet2PieceConfigs_Two2PiecesConfigs_TrueReturned()throws GameException{
    board.getPosition(9).setPositionToken(playerToken);
    board.getPosition(21).setPositionToken(playerToken);
    board.getPosition(22).setPositionToken(playerToken);
    board.printBoard();
    int numberOf2PiecesConfigs = BoardState.get2PieceConfigs(board, playerToken);
    boolean result = false;
    if (numberOf2PiecesConfigs == 2)
      result = true;
    Assert.assertTrue("More or less than 2 TwoPiecesConfigs found", result);
  }

  @Test
  public void testGetBlockedPieces_1PieceBlocked_TrueReturned()throws GameException{
    board.getPosition(9).setPositionToken(playerToken);
    board.getPosition(10).setPositionToken(enemy);
    board.getPosition(3).setPositionToken(playerToken);
    board.getPosition(11).setPositionToken(playerToken);
    board.getPosition(18).setPositionToken(playerToken);
    board.printBoard();
    int numberOfBlockedPieces = BoardState.getEnemyBlockedPieces(board, playerToken);
    boolean result = false;
    if (numberOfBlockedPieces == 1)
      result = true;
    Assert.assertTrue("More or less than 1 blocked Pieces returned", result);
  }


  @Test
  public void testGetMills_2MillsOnBoard_TrueReturned()throws GameException{
    board.getPosition(0).setPositionToken(playerToken);
    board.getPosition(9).setPositionToken(playerToken);
    board.getPosition(21).setPositionToken(playerToken);
    board.getPosition(22).setPositionToken(playerToken);
    board.getPosition(23).setPositionToken(playerToken);
    board.printBoard();
    int numberOfMills = BoardState.getNumberOfMills(board, playerToken);
    boolean result = false;
    if (numberOfMills == 2)
      result = true;
    Assert.assertTrue("More or less 2 Mills returned", result);
  }
}
