//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.core.logic;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.utils.GameStatus;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.game.PositionImpl;
import java.util.Iterator;

public interface Board {
  int NUMBER_OF_POSITIONS = 24;

  void executeMove(Move var1, PositionToken var2);

  Move getLastMove();

  PositionImpl getPosition(int var1) throws GameException;

  int getNumberOfTokensForPlayer(PositionToken var1);

  int getNumberOfMovesForPlayer(PositionToken var1);

  GameStatus getCurrentGameStatus();

  void setCurrentGameStatus(GameStatus var1);

  void printBoard();

  Iterator<Position> iteratePositions();

  void undoMove(Move move) throws GameException;
}
