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
            boolean isMill = rules.willBeMill(board, friendly, i, position.getId());
            if (isMill) {
              removeId = getRemoveId(board);
            }
            moves.add(new Move(i, position.getId(), removeId));
          }
        }
      }
    }
    return moves;
  }

  private static List<Move> possibleFlying(Board board) throws GameException{
    List<Move> moves = new ArrayList<>();
    int removeId = -1;
    for(int i = 0; i< 24; i++){
      if(board.getPosition(i).getPositionToken().equals(friendly)){
        for(int j = 0; j<24; j++){
          if(board.getPosition(j).isAvailable()){
            boolean isMill = rules.willBeMill(board, friendly ,i, j);
            if(isMill){
              removeId = getRemoveId(board);
            }
            moves.add(new Move(i,j,removeId));
          }
        }
      }
    }
    return moves;
  }


  private static int getRemoveId(Board board) throws GameException {
    int removeId;
    do {
      removeId = (int) (Math.random() * 24);
    } while (!rules.isValidRemove(board, enemy, removeId));
    return removeId;
  }
}


