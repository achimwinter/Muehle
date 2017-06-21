//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.remote.utils.models;

import de.fhws.gos.ss17.core.logic.Move;

public class Turn {
  private Integer fromId;
  private Integer toId;
  private Integer removeId;

  public Turn() {
    this.fromId = null;
    this.removeId = null;
    this.toId = null;
  }

  public Turn(Move move) {
    this.fromId = move.getFromId() != -1?Integer.valueOf(move.getFromId()):null;
    this.toId = Integer.valueOf(move.getToId());
    this.removeId = move.getRemoveId() != -1?Integer.valueOf(move.getRemoveId()):null;
  }

  public Turn(int fromId, int toId, int removeId) {
    this.fromId = Integer.valueOf(fromId);
    this.toId = Integer.valueOf(toId);
    this.removeId = Integer.valueOf(removeId);
  }

  public Integer getFromId() {
    return this.fromId;
  }

  public void setFromId(int fromId) {
    this.fromId = Integer.valueOf(fromId);
  }

  public Integer getToId() {
    return this.toId;
  }

  public void setToId(int toId) {
    this.toId = Integer.valueOf(toId);
  }

  public Integer getRemoveId() {
    return this.removeId;
  }

  public void setRemoveId(int removeId) {
    this.removeId = Integer.valueOf(removeId);
  }

  public Move asMove() {
    Move move = new Move();
    move.setFromId(this.fromId != null?this.fromId.intValue():-1);
    move.setToId(this.toId != null?this.toId.intValue():-1);
    move.setRemoveId(this.removeId != null?this.removeId.intValue():-1);
    return move;
  }
}
