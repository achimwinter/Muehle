package de.fhws.gos.ss17.remote.impl;

import de.fhws.gos.ss17.core.network.Connection;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.remote.utils.models.Turn;
import java.io.IOException;

public class RemoteBotPlayer extends RemotePlayer {
  public RemoteBotPlayer(PositionToken playerToken, Connection connection) {
    super(playerToken, connection);
  }

  protected String doPlayGame(Turn turn) throws IOException {
    return this.connection.playBotgame(this.jsonHelper.serialize(turn));
  }
}
