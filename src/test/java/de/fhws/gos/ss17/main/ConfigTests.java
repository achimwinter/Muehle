package de.fhws.gos.ss17.main;

import de.fhws.gos.ss17.core.network.Connection;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * (c) Tobias Fertig, FHWS 2017
 */
public class ConfigTests {

  @Test
  public void testInitConnection_IsImplemented_NoExceptionThrown() {
    boolean exceptionThrown = false;

    try {
      Config.initConnection();
    } catch (Exception e) {
      exceptionThrown = true;
    }

    assertFalse("Exception was thrown, but shouldn't", exceptionThrown);
  }

  @Test
  public void testInitBoard_IsImplemented_NoExceptionThrown() {
    boolean exceptionThrown = false;

    try {
      Config.initBoard();
    } catch (Exception e) {
      exceptionThrown = true;
    }

    assertFalse("Exception was thrown, but shouldn't", exceptionThrown);
  }

  @Test
  public void testGetRemotePlayer_IsImplemented_NoExceptionThrown() {
    boolean exceptionThrown = false;

    try {
      Connection connection = Config.initConnection();
      Config.getRemotePlayer(connection);
    } catch (Exception e) {
      exceptionThrown = true;
    }

    assertFalse("Exception was thrown, but shouldn't", exceptionThrown);
  }

  @Test
  public void testGetRandomPlayer_IsImplemented_NoExceptionThrown() {
    boolean exceptionThrown = false;

    try {
      Config.getRandomPlayer();
    } catch (Exception e) {
      exceptionThrown = true;
    }

    assertFalse("Exception was thrown, but shouldn't", exceptionThrown);
  }

}
