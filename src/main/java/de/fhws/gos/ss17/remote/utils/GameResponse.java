package de.fhws.gos.ss17.remote.utils;

public enum GameResponse {
  NEXT_TURN(100),
  VICTORY(200),
  VICTORY_INVALID_TURN(201),
  VICTORY_TIMEOUT(202),
  LOSS(300),
  LOSS_INVALID_TURN(301),
  TIE(400),
  NOT_A_VALID_RESPONSE(0);

  private int statusCode;

  private GameResponse(int statusCode) {
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public static GameResponse getGameResponseForStatus(int statusCode) {
    GameResponse[] var1 = values();
    int var2 = var1.length;

    for(int var3 = 0; var3 < var2; ++var3) {
      GameResponse gameResponse = var1[var3];
      if(gameResponse.getStatusCode() == statusCode) {
        return gameResponse;
      }
    }

    return NOT_A_VALID_RESPONSE;
  }
}
