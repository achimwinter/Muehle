package de.fhws.gos.ss17.logic;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.utils.PositionToken;

/**
 * Created by awinter on 29.03.17.
 */
public interface Rules {


  boolean isMill(Board board, PositionToken playerToken, int stoneId) throws GameException;

  boolean willBeMill(Board board, PositionToken playerToken, int fromId, int toId)
      throws GameException;

  boolean isValidFrom(Board board, PositionToken playerToken, int fromId) throws GameException;

  boolean isValidTo(Board board, int toId) throws GameException;

  boolean isValidRemove(Board board, PositionToken playerToken, int stoneId) throws GameException;

  boolean isValidMove(Board board, PositionToken playerToken, int fromId, int toId)
      throws GameException;

  boolean isValidPlacement(Board board, int stoneId) throws GameException;

  boolean isValidFlying(Board board, PositionToken playerToken, int fromId, int toId)
      throws GameException;

}
