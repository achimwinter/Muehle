package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.logic.impl.RulesImpl;
import de.fhws.gos.ss17.players.utils.MillCombinations;

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
      do{
        removeId = this.getRemoveIndex(board);
      }while (!rules.isValidRemove(board, playerToken, removeId));

    }
    return new Move(-1, toId, removeId);
  }

  protected Move getMovingMove(Board board) throws GameException {
    int fromId;
    int randomValue;
    do {
      randomValue = (int) (Math.random() * 24);
      fromId = randomValue;
    } while (!rules.isValidFrom(board, playerToken, fromId));

    int toId;
    do {
      toId = (int) (Math.random() * 24);
    } while (!rules.isValidMove(board, playerToken, fromId, toId));

    int removeId = -1;
    if (rules.willBeMill(board, playerToken, fromId, toId)) {
      do {
        randomValue = (int) (Math.random()
            * 24);
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
    if (rules.willBeMill(board, playerToken, fromId, toId)) {
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
      removeId = (int) (Math.random() * 24);
      if (board.getPosition(removeId).getPositionToken().equals(opponent) && (
          !rules.isMill(board, opponent, removeId) || rules.allInMill(board, opponent))) {
        repeat = false;
      }
    } while (repeat);
    return removeId;
  }
}