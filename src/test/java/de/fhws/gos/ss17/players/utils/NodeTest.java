package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.ss17.game.Board;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.main.Config;
import org.junit.Test;

/**
 * Created by awinter on 08.06.17.
 */
public class NodeTest {

  private Board board = Config.initBoard();
  private PositionToken playerToken = PositionToken.PLAYER_ONE;

  @Test
  public void testNode_differentBoardsprinted_Boardsprinted() throws GameException {
    board.getPosition(0).setPositionToken(playerToken);
    board.getPosition(23).setPositionToken(PositionToken.PLAYER_TWO);

    Node node = new Node(board, Phase.MOVING, 3, PositionToken.PLAYER_ONE);
  }

}
