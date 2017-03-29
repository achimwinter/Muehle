package de.fhws.gos.ss17.logic;

import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Player;
import de.fhws.gos.core.utils.PositionToken;

/**
 * Created by awinter on 29.03.17.
 */
public interface Rules {

  boolean isMill(Board board,PositionToken playerToken, int stoneId);
  boolean willBeMill(Board board, PositionToken playerToken, int fromId, int toId);
  boolean isValidFrom(Board board, PositionToken playerToken, int fromId);
  boolean isValidTo(Board board, PositionToken playerToken, int toId);
  boolean isValidRemove(Board board, PositionToken playerToken, int stoneId);
  boolean isValidMove(Board board, PositionToken playerToken, int fromId, int toId);
  boolean isValidPlacement(Board board, int stoneId);
  boolean isValidFlying(Board board, PositionToken playerToken, int fromId, int toId);

}
