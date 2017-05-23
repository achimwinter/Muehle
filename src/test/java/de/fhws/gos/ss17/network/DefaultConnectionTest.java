package de.fhws.gos.ss17.network;

import java.io.IOException;
import org.junit.Test;

/**
 * Created by Achim on 18.05.2017.
 */
public class DefaultConnectionTest {
  DefaultConnection defaultConnection = new DefaultConnection();

  public DefaultConnectionTest() throws IOException {
  }

  @Test
  public void testCreateGame_gameIdSet_gameIdSet() throws IOException {
    defaultConnection.createBotgame();
  }

  @Test
  public void testJoinBotGame_GameInJsonReturned_StringReceived() throws IOException {
    defaultConnection.createBotgame();
    System.out.println(defaultConnection.joinBotgame());
  }

}
