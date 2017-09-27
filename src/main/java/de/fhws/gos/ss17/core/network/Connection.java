//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package de.fhws.gos.ss17.core.network;

import java.io.IOException;
import java.io.InputStream;

public interface Connection {
  String joinBotgame() throws IOException;

  String readResponse(InputStream var1) throws IOException;

  String playBotgame(String var1) throws IOException;

  String offerGame() throws IOException;

  String joinGame() throws IOException;

  String playGame(String var1) throws IOException;

  void setGameId(String var1);

  String joinVersusGame()throws IOException;
}
