package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.logic.Position;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.logic.impl.RulesImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by awinter on 09.05.17.
 */
public class PossibleMoves {

  private static final RulesImpl rules = new RulesImpl();

  public static List<Move> getPossibleMoves(Board board, Phase phase, PositionToken playerToken){
    try {
      switch (phase) {
        case PLACING:
          return possiblePlacement(board, playerToken);
        case MOVING:
          return possibleMoves(board, playerToken);
        case FLYING:
          return possibleFlying(board, playerToken);
        default:
          return null;
      }
    } catch (GameException ex){
      ex.printStackTrace();
    }
    return null;
  }

  private static List<Move> possiblePlacement(Board board, PositionToken playerToken) throws GameException {
    List<Move> moves = new ArrayList<>();
    int removeId = -1;
    for (int i = 0; i < 24; i++) {
      if (board.getPosition(i).getPositionToken().equals(PositionToken.IS_EMPTY)) {
        boolean isMill = rules.willBeMill(board, playerToken, -1, i);
        if (isMill) {
          removeId = getRemoveId(board, playerToken);
        }
        moves.add(new Move(-1, i, removeId));
        removeId = -1;
      }
    }
    return moves;
  }


  private static List<Move> possibleMoves(Board board, PositionToken playerToken) throws GameException {
    List<Move> moves = new ArrayList<>();
    int removeId = -1;
    for (int i = 0; i < 24; i++) {
      if (board.getPosition(i).getPositionToken().equals(playerToken)) {
        for (Position position : board.getPosition(i).getNeighbors()) {
          if (position.isAvailable()) {
            boolean isMill = rules.willBeMill(board, playerToken, i , position.getId());
            if (isMill) {
              removeId = getRemoveId(board, playerToken);
            }
            moves.add(new Move(i, position.getId(), removeId));
            removeId = -1;
          }
        }
      }
    }
    return moves;
  }

  private static List<Move> possibleFlying(Board board, PositionToken playerToken) throws GameException {
    List<Move> moves = new ArrayList<>();
    int removeId = -1;
    for (int i = 0; i < 24; i++) {
      if (board.getPosition(i).getPositionToken().equals(playerToken)) {
        for (int j = 0; j < 24; j++) {
          if (board.getPosition(j).isAvailable()) {
            boolean isMill = rules.willBeMill(board, playerToken, i, j);
            if (isMill) {
              removeId = getRemoveId(board, playerToken);
            }
            moves.add(new Move(i, j, removeId));
            removeId = -1;
          }
        }
      }
    }
    return moves;
  }


  public static int getRemoveId(Board board, PositionToken playerToken) throws GameException {
    PositionToken enemy = (playerToken.equals(PositionToken.PLAYER_ONE)) ? PositionToken.PLAYER_TWO : PositionToken.PLAYER_ONE;
    int removeId = -1;
    int highestEnemy = -1;
    int[] positions = new int[24];
    for (int i = 0; i < 24; i++) {
      positions[i] = -1;
    }
    for (int i = 0; i < 24; i++) {
      if (board.getPosition(i).getPositionToken().equals(enemy)) {
        positions[i] = enemyAround(board, i, enemy);
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


  private static int enemyAround(Board board, int stoneId, PositionToken enemy) throws GameException {
    int counter = 0;
    for (Position position : board.getPosition(stoneId).getNeighbors()) {
      if (position.getPositionToken().equals(enemy)) {
        counter++;
      }
    }
    return counter;
  }
}


