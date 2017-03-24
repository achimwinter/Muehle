package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.players.impl.AbstractPlayer;
import de.fhws.gos.players.utils.MillCombinations;
import java.util.Scanner;

/**
 * Created by Achim on 24.03.2017.
 */
public class HumanPlayer extends AbstractPlayer {

  public HumanPlayer(PositionToken playerToken) {
    super(playerToken);
  }

  protected Move getPlacingMove(Board board) throws GameException {
    int removeID = -1;
    System.out.print("ID des zu setzenden Steines eingeben: ");
    Scanner input = new Scanner(System.in);
    int removeId = -1;

    int toId = input.nextInt();
    if (MillCombinations.getInstance(board).isMill(this.playerToken, toId)) {
      System.out.print("ID des zu entfernenden Steines eingeben: ");
      removeId = input.nextInt();
    }

    return new Move(-1, toId, removeId);
  }


}
