package de.fhws.gos.ss17.remote.utils.models;

public class FieldEntry {
  private int id;
  private int token;

  public FieldEntry() {
  }

  public FieldEntry(int id, int token) {
    this.id = id;
    this.token = token;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getToken() {
    return this.token;
  }

  public void setToken(int token) {
    this.token = token;
  }
}
