package de.fhws.gos.ss17.logic.impl;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.players.MillCombinations;
import de.fhws.gos.ss17.logic.Rules;

/**
 * Created by awinter on 29.03.17.
 */

public class RulesImpl implements Rules {

  @Override
  public boolean isMill(Board board, PositionToken playerToken, int stoneId) throws GameException {
    return MillCombinations.getInstance(board).isMill(playerToken, stoneId);
  }

  @Override
  public boolean willBeMill(Board board, PositionToken playerToken, int fromId, int toId)
      throws GameException {
    return MillCombinations.getInstance(board).willBeMill(playerToken, fromId, toId);
  }

  @Override
  public boolean isValidFrom(Board board, PositionToken playerToken, int fromId)
      throws GameException {
    boolean playerOwnsPosition = board.getPosition(fromId).getPositionToken().equals(playerToken);
    boolean positionHasFreeNeighbors = false;
    Position[] neighbors = board.getPosition(fromId).getNeighbors();

    for(int i = 0; i < neighbors.length; ++i){
      Position position = neighbors[i];
      if(position.isAvailable()){
        positionHasFreeNeighbors = true;
        break;
      }
    }
    return playerOwnsPosition && positionHasFreeNeighbors;
  }

  @Override
  public boolean isValidTo(Board board, int toId)
      throws GameException {
    return board.getPosition(toId).getPositionToken().equals(PositionToken.IS_EMPTY);
  }

  @Override
  public boolean isValidRemove(Board board, PositionToken playerToken, int stoneId)
      throws GameException {
    return board.getPosition(stoneId).getPositionToken().equals(PositionToken.PLAYER_TWO)
        && !(MillCombinations.getInstance(board).isMill(PositionToken.PLAYER_TWO, stoneId)) ||
        MillCombinations.getInstance(board).allInMill(PositionToken.PLAYER_TWO);
  }

  @Override
  public boolean isValidMove(Board board, PositionToken playerToken, int fromId, int toId)
      throws GameException {

    boolean positionHasFreeNeighbors = false;
    Position[] neighbors = board.getPosition(fromId).getNeighbors();
    //int var6 = neighbors.length;

    for (int i = 0; i < neighbors.length; ++i) {
      Position position = neighbors[i];
      if (position.getId() == toId) {
        positionHasFreeNeighbors = true;
        return isValidFrom(board, playerToken, fromId) && positionHasFreeNeighbors;
      }
    }
    return positionHasFreeNeighbors;
  }

  @Override
  public boolean isValidPlacement(Board board, int stoneId) throws GameException {
    return this.isValidTo(board, stoneId);
  }

  @Override
  public boolean isValidFlying(Board board, PositionToken playerToken, int fromId, int toId)
      throws GameException {
    return this.isValidFrom(board, playerToken, fromId) && this.isValidTo(board, toId) && (
        board.getNumberOfTokensForPlayer(playerToken) == 3);
  }

}
