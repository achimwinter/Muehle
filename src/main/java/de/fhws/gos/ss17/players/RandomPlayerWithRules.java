package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.players.AbstractPlayer;
import de.fhws.gos.ss17.logic.impl.RulesImpl;
import de.fhws.gos.ss17.players.MillCombinations;

public class RandomPlayerWithRules extends AbstractPlayer {

  RulesImpl rules = new RulesImpl();

  public RandomPlayerWithRules(PositionToken playerToken) {
    super(playerToken);
  }

  public Move getNextMove(Board board) throws GameException {
    return super.getNextMove(board);
  }

  protected Move getPlacingMove(Board board) throws GameException {
    int removeId = -1;
    int toId;

    do {
      toId = (int) (Math.random() * 24);
    } while (!rules.isValidPlacement(board, toId));

    if (rules.willBeMill(board, playerToken, -1, toId)) {
      removeId = this.getRemoveIndex(board);
    }
    return new Move(-1, toId, removeId);
  }

  protected Move getMovingMove(Board board) throws GameException {
    int fromId;
    int randomValue;
    do {
      randomValue = (int) (Math.random() * 24);
      fromId = randomValue;
    } while (!this.validateFrom(board, fromId));

    int toId;
    do {
      toId = (int) (Math.random() * 24); //weiß nicht, wie man die Regeln der Bewegung implementiert
    } while (!board.getPosition(toId).isAvailable());

    int removeId = -1;
    if (MillCombinations.getInstance(board).isMill(this.playerToken, fromId, toId)) {
      do {
        randomValue = (int) (Math.random()
            * 24); //da muss man irgendwie noch checken, ob der Stein einem selbst gehört oder halt nicht
        removeId = randomValue;
      } while (!rules.isValidRemove(board, playerToken, removeId));

    }
    return new Move(fromId, toId, removeId);
  }

  @Override
  protected Move getFlyingMove(Board board) throws GameException {
    int randomValue;
    int fromId;
    do {
      randomValue = (int) (Math.random() * 24);
      fromId = randomValue;
    } while (!this.validateFrom(board, fromId));

    int toId;
    do {
      toId = (int) (Math.random() * 24);
    } while (!board.getPosition(toId).isAvailable());

    int removeId = -1;
    if (MillCombinations.getInstance(board).isMill(this.playerToken, fromId, toId)) {
      removeId = this.getRemoveIndex(board);
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

  private int getRemoveIndex(Board board) throws GameException {
    boolean repeat = true;
    PositionToken opponent =
        this.playerToken.equals(PositionToken.PLAYER_ONE) ? PositionToken.PLAYER_TWO
            : PositionToken.PLAYER_ONE;

    int removeId;
    do {
      System.out.print("ID des zu entfernenden Steins eingeben: ");
      removeId = (int) (Math.random() * 24);
      if (board.getPosition(removeId).getPositionToken().equals(opponent) && (
          !MillCombinations.getInstance(board).isMill(opponent, removeId) || MillCombinations
              .getInstance(board).allInMill(opponent))) {
        repeat = false;
      }
    } while (repeat);
    return removeId;
  }
}