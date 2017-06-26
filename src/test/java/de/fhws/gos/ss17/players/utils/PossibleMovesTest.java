package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.utils.PositionToken;
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
  public void testGetRemoveId_RemoveIDReceived_TrueReturned() throws GameException{
    board.getPosition(17).setPositionToken(playerToken);
    board.getPosition(18).setPositionToken(playerToken);
    board.getPosition(19).setPositionToken(playerToken);
    board.getPosition(20).setPositionToken(playerToken);
    board.getPosition(21).setPositionToken(playerToken);
    board.getPosition(22).setPositionToken(playerToken);
    board.getPosition(23).setPositionToken(playerToken);

    board.getPosition(1).setPositionToken(PositionToken.PLAYER_TWO);
    board.getPosition(2).setPositionToken(PositionToken.PLAYER_TWO);
    board.getPosition(4).setPositionToken(PositionToken.PLAYER_TWO);
    board.getPosition(7).setPositionToken(PositionToken.PLAYER_TWO);
    board.getPosition(11).setPositionToken(PositionToken.PLAYER_TWO);
    board.getPosition(12).setPositionToken(PositionToken.PLAYER_TWO);


    board.printBoard();
    List<Move> moves = PossibleMoves.getPossibleMoves(board, Phase.MOVING, playerToken);
    moves.forEach(x -> System.out.println(x.getFromId() + " TO : " + x.getToId() + " REM: " + x.getRemoveId()));

  }

  @Test
  public void testGetMoves_2MovesGotten_MovesPrinted() throws GameException{
    board.executeMove((new Move(-1, 4, -1)), playerToken);
    List<Move> moves=  PossibleMoves.getPossibleMoves(board, Phase.MOVING, playerToken);
    for(Move move: moves){
      System.out.println("From: " + move.getFromId() + " TO: " + move.getToId() + " Remove " + move.getRemoveId());
    }
  }

  @Test
  public void testGetBestMove_MillGotten_MovePrinted() throws GameException{
    board.executeMove((new Move(-1, 19, -1)), playerToken);
    board.executeMove((new Move(-1, 18, -1)), playerToken);
    board.executeMove((new Move(-1, 13, -1)), playerToken);
    board.executeMove((new Move(-1, 3, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 21, -1)), playerToken);
    board.executeMove((new Move(-1, 0, -1)), PositionToken.PLAYER_TWO);
    board.executeMove((new Move(-1, 23, -1)), PositionToken.PLAYER_TWO);
    Move move = EvaluateMoves.getBestMove(board, Phase.MOVING);
    System.out.println("From: " + move.getFromId() + " TO: " + move.getToId() + " Remove " + move.getRemoveId());
  }

  @Test
  public void testGetBestMove_MoveToAlly_MovePrinted() throws GameException{
    board.executeMove((new Move(-1, 21, -1)), playerToken);
    board.executeMove((new Move(-1, 19, -1)), playerToken);
    board.executeMove((new Move(-1, 2, -1)), playerToken);
    board.executeMove((new Move(-1, 12, -1)), playerToken);
    board.executeMove((new Move(-1, 18, -1)), playerToken);
    //board.executeMove((new Move(-1, 0, -1)), PositionToken.PLAYER_TWO);
    for(int i = 0; i< 20 ;i++) {
      Move move = EvaluateMoves.getBestMove(board, Phase.MOVING);
      System.out.println(
          "From: " + move.getFromId() + " TO: " + move.getToId() + " Remove " + move.getRemoveId());

      board.printBoard();
      board.executeMove(move,playerToken);
    }
  }

}


