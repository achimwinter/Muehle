package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.players.impl.RandomPlayerWithRules;
import java.util.Scanner;

/**
 * Created by awinter on 04.04.17.
 */
public class CyborgPlayer extends RandomPlayerWithRules{


  private HumanPlayer human;
  private RandomPlayerWithRules randomPlayer;
  private Scanner input = new Scanner(System.in);

  char change = 'n';

  public CyborgPlayer(PositionToken playerToken) {
    super(playerToken);
  }

  protected Move getPlacingMove(Board board)throws GameException{
    Move placement = super.getPlacingMove(board);
    System.out.println("Stein wird gesetzt auf: " + placement.getToId());
    System.out.print("Möchten Sie den Zug verändern?(y/n)");
    change = input.findInLine(".").charAt(0);

    if(change == 'y'){
      placement = human.getPlacingMove(board);
    }
    return placement;
  }

  protected Move getMovingMove(Board board) throws GameException{
    Move move = super.getMovingMove(board);
    System.out.println("Stein wird verschoben von: " + move.getFromId() + " nach: " + move.getToId());
    change = input.findInLine(".").charAt(0);

    if(change == 'y'){
      move = human.getMovingMove(board);
    }
    return move;
  }


  protected Move getFlyingMove(Board board) throws GameException{
    Move move = super.getFlyingMove(board);
    System.out.println("Stein wird verschoben von: " + move.getFromId() + " nach: " + move.getToId());
    change = input.findInLine(".").charAt(0);

    if (change == 'y'){
      move = human.getFlyingMove(board);
    }
    return move;
  }





}
