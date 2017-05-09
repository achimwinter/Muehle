package de.fhws.gos.ss17.logic.impl;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.exceptions.CheckedExceptions;
import de.fhws.gos.ss17.exceptions.UncheckedExceptions;
import de.fhws.gos.ss17.logic.Rules;
import de.fhws.gos.ss17.players.utils.MillCombinations;

/**
 * Created by awinter on 29.03.17.
 */

public class RulesImpl implements Rules {

  @Override
  public boolean isMill(Board board, PositionToken playerToken, int stoneId) throws GameException {
    try {
      return MillCombinations.getInstance(board).isMill(playerToken, stoneId);
    }
    catch (GameException e){
      CheckedExceptions.catchGameException(e);
      return false;
    }
    catch(NullPointerException e){
      UncheckedExceptions.getNullPointerException(e);
      return false;
    }
    catch(ArrayIndexOutOfBoundsException e){
      UncheckedExceptions.getArrayIndexOutOfBoundsException(e);
      return false;
    }
  }

  @Override
  public boolean willBeMill(Board board, PositionToken playerToken, int fromId, int toId)
      throws GameException {
    try {
      return MillCombinations.getInstance(board).willBeMill(playerToken, fromId, toId);
    }
    catch (GameException e){
      CheckedExceptions.catchGameException(e);
      return false;
    }
    catch(NullPointerException e){
      UncheckedExceptions.getNullPointerException(e);
      return false;
    }
    catch(ArrayIndexOutOfBoundsException e){
      UncheckedExceptions.getArrayIndexOutOfBoundsException(e);
      return false;
    }
  }

  @Override
  public boolean isValidFrom(Board board, PositionToken playerToken, int fromId)
      throws GameException {
    boolean positionHasFreeNeighbors = false;
    boolean playerOwnsPosition;
    Position[] neighbors = board.getPosition(fromId).getNeighbors();

    try {
      playerOwnsPosition = board.getPosition(fromId).getPositionToken().equals(playerToken);
    }catch (GameException e){
      CheckedExceptions.catchGameException(e);
      return false;
    }


    for (int i = 0; i < neighbors.length; ++i) {
      Position position = neighbors[i];
      if (position.isAvailable()) {
        positionHasFreeNeighbors = true;
        break;
      }
    }
    return playerOwnsPosition && positionHasFreeNeighbors;
  }

  @Override
  public boolean isValidTo(Board board, int toId)
      throws GameException {
    try {
      return board.getPosition(toId).getPositionToken().equals(PositionToken.IS_EMPTY);
    }catch (GameException e){
      CheckedExceptions.catchGameException(e);
      return false;
    }
  }

  @Override
  public boolean isValidRemove(Board board, PositionToken playerToken, int stoneId)
      throws GameException {
    try {
      return board.getPosition(stoneId).getPositionToken().equals(PositionToken.PLAYER_TWO)
          && !(MillCombinations.getInstance(board).isMill(PositionToken.PLAYER_TWO, stoneId)) ||
          MillCombinations.getInstance(board).allInMill(PositionToken.PLAYER_TWO);
    }catch (GameException e){
      CheckedExceptions.catchGameException(e);
      return false;
    }
  }

  @Override
  public boolean isValidMove(Board board, PositionToken playerToken, int fromId, int toId)
      throws GameException {

    boolean positionHasFreeNeighbors = false;
    Position[] neighbors = board.getPosition(fromId).getNeighbors();

    for (int i = 0; i < neighbors.length; ++i) {
      Position position = neighbors[i];
      if (position.isAvailable() && position.getId() == toId) {
        positionHasFreeNeighbors = true;
      }
    }
    return isValidFrom(board, playerToken, fromId) && positionHasFreeNeighbors;
  }

  @Override
  public boolean isValidPlacement(Board board, int stoneId) throws GameException {
    return this.isValidTo(board, stoneId);
  }

  @Override
  public boolean isValidFlying(Board board, PositionToken playerToken, int fromId, int toId)
      throws GameException {
    return this.isValidPlacement(board, fromId) && this.isValidTo(board, toId) && (
        board.getNumberOfTokensForPlayer(playerToken) == 3);
  }

}