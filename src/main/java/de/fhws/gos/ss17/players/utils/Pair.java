package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.ss17.core.logic.Move;

/**
 * Created by awinter on 10.05.17.
 */
public class Pair {
  Move move;
  int value;

  public Pair(Move move, int value) {
    this.move = move;
    this.value = value;
  }

  public Move getMove() {
    return move;
  }

  public void setMove(Move move) {
    this.move = move;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
