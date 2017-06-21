//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.remote.utils.models;

public class TurnResult {
  private String status;
  private FieldEntry[] field;
  private Turn turn;

  public TurnResult() {
  }

  public TurnResult(String status, FieldEntry[] field, Turn turn) {
    this.status = status;
    this.field = field;
    this.turn = turn;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public FieldEntry[] getField() {
    return this.field;
  }

  public void setField(FieldEntry[] field) {
    this.field = field;
  }

  public Turn getTurn() {
    return this.turn;
  }

  public void setTurn(Turn turn) {
    this.turn = turn;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.field[0].getToken() + " - - - - - " + this.field[1].getToken() + " - - - - - " + this.field[2].getToken() + "\n");
    sb.append("|           |           |\n");
    sb.append("|     " + this.field[3].getToken() + " - - " + this.field[4].getToken() + " - - " + this.field[5].getToken() + "     |" + "\n");
    sb.append("|     |     |     |     |\n");
    sb.append("|     | " + this.field[6].getToken() + " - " + this.field[7].getToken() + " - " + this.field[8].getToken() + " |     |");
    sb.append("|     | |       | |     |");
    sb.append(this.field[9].getToken() + " - - " + this.field[10].getToken() + "-" + this.field[11].getToken() + "       " + this.field[12].getToken() + "-" + this.field[13].getToken() + " - - " + this.field[14].getToken());
    sb.append("|     | |       | |     |");
    sb.append("|     | " + this.field[15].getToken() + " - " + this.field[16].getToken() + " - " + this.field[17].getToken() + " |     |");
    sb.append("|     |     |     |     |");
    sb.append("|     " + this.field[18].getToken() + " - - " + this.field[19].getToken() + " - - " + this.field[20].getToken() + "     |");
    sb.append("|           |           |");
    sb.append(this.field[21].getToken() + " - - - - - " + this.field[22].getToken() + " - - - - - " + this.field[23].getToken());
    return sb.toString();
  }
}
