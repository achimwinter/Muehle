//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.core.logic;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.utils.PositionToken;

public interface Player {
  Move getNextMove(Board var1) throws GameException;

  boolean canFly();

  PositionToken getPlayerToken();
}
