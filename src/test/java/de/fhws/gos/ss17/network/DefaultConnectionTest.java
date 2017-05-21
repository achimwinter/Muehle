package de.fhws.gos.ss17.network;

import java.io.IOException;
import org.junit.Test;

/**
 * Created by Achim on 18.05.2017.
 */
public class DefaultConnectionTest {
  DefaultConnection defaultConnection = new DefaultConnection();

  @Test
  public void testGetAuthToken_TokenReceived_StringReceived() throws IOException {
String test;
    defaultConnection.signIn();
    System.out.println(defaultConnection.authorizationToken);
  }

  @Test
  public void testCreateGame_gameIDReceived_StringReceived() throws IOException {
    String gameIdJSON = defaultConnection.playBotgame("test");
    System.out.println(gameIdJSON);
  }

}
