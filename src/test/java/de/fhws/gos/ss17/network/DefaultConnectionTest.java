package de.fhws.gos.ss17.network;

import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import java.io.IOException;
import org.junit.Test;

/**
 * Created by Achim on 18.05.2017.
 */
public class DefaultConnectionTest {


  public DefaultConnectionTest() throws IOException {
  }

  @Test
  public void testCreateGame_gameIdSet_gameIdSet() throws IOException {
    DefaultConnection defaultConnection = new DefaultConnection();
    defaultConnection.createBotgame();
  }

  @Test
  public void testJoinBotGame_GameInJsonReturned_StringReceived() throws IOException {
    DefaultConnection defaultConnection = new DefaultConnection();
    defaultConnection.createBotgame();
    System.out.println(defaultConnection.authorizationToken);
    System.out.println(defaultConnection.joinBotgame());
  }

  @Test
  public void testPlayBotGame_TurnMade_AnswerGotten() throws IOException{
    DefaultConnection defaultConnection = new DefaultConnection();
    defaultConnection.createBotgame();
    String game = defaultConnection.joinBotgame();
    System.out.println(game);
    String turn = JsonConverter.serializeMoveJSON(new Move(-1, 15, -1));
    System.out.println(turn);
    String test = defaultConnection.playBotgame(turn);
    System.out.println(test);
    Game abc = JsonConverter.deserializeGameJSON(test);
    Board board = abc.getBoard(abc.getBoardState());
    board.printBoard();
  }

}
