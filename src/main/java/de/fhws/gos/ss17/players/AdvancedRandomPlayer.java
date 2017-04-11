package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.logic.impl.RulesImpl;

/**
 * Created by Achim on 10.04.2017.
 */
public class AdvancedRandomPlayer extends RandomPlayerWithRules {

  private RulesImpl rules = new RulesImpl();

  public AdvancedRandomPlayer(PositionToken playerToken) {
    super(playerToken);
  }

  public Move getNextMove(Board board) throws GameException {
    return super.getNextMove(board);
  }

  @Override
  protected Move getMovingMove(Board board) throws GameException {
    int fromId;
    int toId = -1;
    int removeId = -1;
    boolean foundToId = false;

    do {
      fromId = (int) (Math.random() * 24);
    } while (!rules.isValidFrom(board, playerToken, fromId));

    Position[] neighbors = board.getPosition(fromId).getNeighbors();
    for (int i = 0; i < neighbors.length; i++) {
      if(neighbors[i].isAvailable() && rules.willBeMill(board, playerToken, fromId, neighbors[i].getId())) {
        toId = neighbors[i].getId();
        foundToId = true;
      }
    }
    if(foundToId == false){
      for(int i = 0; i < neighbors.length; i++){
        if(neighbors[i].isAvailable())
          toId = neighbors[i].getId();
      }

    }

    if (rules.willBeMill(board,  playerToken, fromId,toId)) {
      do {
        removeId = (int) (Math.random() * 24);
      } while (!rules.isValidRemove(board, playerToken, removeId));

    }
    return new Move(fromId, toId, removeId);
  }


}
