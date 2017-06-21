//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.core.logic;

import de.fhws.gos.core.utils.PositionToken;

public interface Position {
  int getId();

  void setNeighbors(Position[] var1);

  Position[] getNeighbors();

  PositionToken getPositionToken();

  void setPositionToken(PositionToken var1);

  boolean isAvailable();
}
