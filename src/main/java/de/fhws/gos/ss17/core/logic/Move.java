//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.core.logic;

public class Move {
  private int fromId;
  private int toId;
  private int removeId;

  public Move() {
    this.fromId = -1;
    this.toId = -1;
    this.removeId = -1;
  }

  public Move(int fromId, int toId, int removeId) {
    this.fromId = fromId;
    this.toId = toId;
    this.removeId = removeId;
  }

  public int getFromId() {
    return this.fromId;
  }

  public void setFromId(int fromId) {
    this.fromId = fromId;
  }

  public int getToId() {
    return this.toId;
  }

  public void setToId(int toId) {
    this.toId = toId;
  }

  public int getRemoveId() {
    return this.removeId;
  }

  public void setRemoveId(int removeId) {
    this.removeId = removeId;
  }
}
