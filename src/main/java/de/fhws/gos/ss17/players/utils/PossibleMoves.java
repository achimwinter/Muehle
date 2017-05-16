package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.logic.impl.RulesImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by awinter on 09.05.17.
 */
public class PossibleMoves {

  private static final RulesImpl rules = new RulesImpl();
  private static final PositionToken friendly = PositionToken.PLAYER_ONE;
  private static final PositionToken enemy = PositionToken.PLAYER_TWO;

  public static List<Move> getPossibleMoves(Board board, Phase phase) throws GameException {
    switch (phase) {
      case PLACING:
        return possiblePlacement(board);
      case MOVING:
        return possibleMoves(board);
      case FLYING:
        return possibleFlying(board);
      default:
        return null;
    }
  }

  private static List<Move> possiblePlacement(Board board) throws GameException {
    List<Move> moves = new ArrayList<>();
    int removeId = -1;
    for (int i = 0; i < 24; i++) {
      if (board.getPosition(i).getPositionToken().equals(PositionToken.IS_EMPTY)) {
        boolean isMill = rules.willBeMill(board, friendly, -1, i);
        if (isMill) {
          removeId = getRemoveId(board);
        }
        moves.add(new Move(-1, i, removeId));
        removeId = -1;
      }
    }
    return moves;
  }


  private static List<Move> possibleMoves(Board board) throws GameException {
    List<Move> moves = new ArrayList<>();
    int removeId = -1;
    for (int i = 0; i < 24; i++) {
      if (board.getPosition(i).getPositionToken().equals(friendly)) {
        for (Position position : board.getPosition(i).getNeighbors()) {
          if (position.isAvailable()) {
            boolean isMill = MillCombinations.getInstance(board)
                .willBeMill(friendly, i, position.getId());
            if (isMill) {
              removeId = getRemoveId(board);
            }
            moves.add(new Move(i, position.getId(), removeId));
            removeId = -1;
          }
        }
      }
    }
    return moves;
  }

  private static List<Move> possibleFlying(Board board) throws GameException {
    List<Move> moves = new ArrayList<>();
    int removeId = -1;
    for (int i = 0; i < 24; i++) {
      if (board.getPosition(i).getPositionToken().equals(friendly)) {
        for (int j = 0; j < 24; j++) {
          if (board.getPosition(j).isAvailable()) {
            boolean isMill = rules.willBeMill(board, friendly, i, j);
            if (isMill) {
              removeId = getRemoveId(board);
            }
            moves.add(new Move(i, j, removeId));
            removeId = -1;
          }
        }
      }
    }
    return moves;
  }


  public static int getRemoveId(Board board) throws GameException {
    int removeId = -1;
    int highestEnemy = -1;
    int[] positions = new int[24];
    for (int i = 0; i < 24; i++) {
      positions[i] = -1;
    }
    for (int i = 0; i < 24; i++) {
      if (board.getPosition(i).getPositionToken().equals(enemy)) {
        positions[i] = enemyAround(board, i);
      }
    }
    for (int i = 0; i < 24; i++) {
      if (positions[i] > highestEnemy) {
        if (rules.allInMill(board, enemy)) {
          highestEnemy = positions[i];
          removeId = i;
        } else if (!rules.isMill(board, enemy, i)) {
          highestEnemy = positions[i];
          removeId = i;
        }
      }
    }
    return removeId;
  }


  private static int enemyAround(Board board, int stoneId) throws GameException {
    int counter = 0;
    for (Position position : board.getPosition(stoneId).getNeighbors()) {
      if (position.getPositionToken().equals(enemy)) {
        counter++;
      }
    }
    return counter;
  }
}


