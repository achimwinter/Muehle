//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.core.logic;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;

public interface Player {
  Move getNextMove(Board var1) throws GameException;

  boolean canFly();

  PositionToken getPlayerToken();
}
