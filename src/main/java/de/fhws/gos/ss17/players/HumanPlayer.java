package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.logic.impl.RulesImpl;
import de.fhws.gos.ss17.players.utils.MillCombinations;
import java.util.Scanner;

/**
 * Created by Achim on 24.03.2017.
 */
public class HumanPlayer extends AbstractPlayer {

  private RulesImpl rules = new RulesImpl();

  public HumanPlayer(PositionToken playerToken) {
    super(playerToken);
  }

  protected Move getPlacingMove(Board board) throws GameException {
    Scanner input = new Scanner(System.in);
    int removeId = -1;

    System.out.print("ID des zu setzenden Steines eingeben: ");
    int toId;

    do {
      toId = input.nextInt();
    } while (!board.getPosition(toId).isAvailable());

    if (rules.willBeMill(board, playerToken, -1, toId)) {
      System.out.print("ID des zu entfernenden Steines eingeben: ");
      removeId = this.getRemoveIndex(board);
    }
    return new Move(-1, toId, removeId);
  }

  private int getRemoveIndex(Board board) throws GameException {
    Scanner input = new Scanner(System.in);
    boolean repeat = true;
    PositionToken opponent =
        this.playerToken.equals(PositionToken.PLAYER_ONE) ? PositionToken.PLAYER_TWO
            : PositionToken.PLAYER_ONE;

    int removeId;
    do {
      System.out.print("ID des zu entfernenden Steins eingeben: ");
      removeId = input.nextInt();
      if (board.getPosition(removeId).getPositionToken().equals(opponent) && (
          !MillCombinations.getInstance(board).isMill(opponent, removeId) || MillCombinations
              .getInstance(board).allInMill(opponent))) {
        repeat = false;
      }
    } while (repeat);
    return removeId;
  }

  protected Move getMovingMove(Board board) throws GameException {
    int fromId;
    Scanner input = new Scanner(System.in);
    do {
      System.out.print("ID des zu bewegenden Steins eingeben: ");
      fromId = input.nextInt();
    } while (!this.validateFrom(board, fromId));

    int toId;
    do {
      System.out.print("ID der neuen Position eingeben: ");
      toId = input.nextInt();
    } while (!board.getPosition(toId).isAvailable());

    int removeId = -1;
    if (rules.willBeMill(board, playerToken, fromId, toId)) {
      System.out.print("ID des zu entfernenden Steins eingeben: ");
      removeId = input.nextInt();
    }
    return new Move(fromId, toId, removeId);
  }

  private boolean validateFrom(Board board, int fromId) throws GameException {
    boolean playerOwnsPosition = board.getPosition(fromId).getPositionToken()
        .equals(this.playerToken);
    boolean positionHasFreeNeighbors = false;
    Position[] var5 = board.getPosition(fromId).getNeighbors();
    int var6 = var5.length;

    for (int var7 = 0; var7 < var6; ++var7) {
      Position position = var5[var7];
      if (position.isAvailable()) {
        positionHasFreeNeighbors = true;
        break;
      }
    }

    return playerOwnsPosition && positionHasFreeNeighbors;

  }

  protected Move getFlyingMove(Board board) throws GameException {
    Scanner input = new Scanner(System.in);
    int fromId;
    do {
      System.out.print("ID des zu bewegenden Steins eingeben: ");
      fromId = input.nextInt();
    } while (!this.validateFrom(board, fromId));

    int toId;
    do {
      toId = input.nextInt();
    } while (!board.getPosition(toId).isAvailable());

    int removeId = -1;
    if (rules.willBeMill(board, playerToken, fromId, toId)) {
      removeId = this.getRemoveIndex(board);
    }

    return new Move(fromId, toId, removeId);
  }
}
