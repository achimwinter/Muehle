package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.players.utils.Node;

/**
 * Created by Achim on 08.06.2017.
 */
public class NegamaxPlayer extends AbstractPlayer{

  //generate possible Moves
  //getBoardScore
  //Copy Board

  public NegamaxPlayer(PositionToken playerToken){
    super(playerToken);
  }

  @Override
  protected Move getPlacingMove(Board var1) throws GameException {
    return null;
  }

  @Override
  protected Move getMovingMove(Board var1) throws GameException {
    return null;
  }

  @Override
  protected Move getFlyingMove(Board var1) throws GameException {
    return null;
  }

  public Move negamax(Node node, int depth, int alpha, int beta, PositionToken playerToken){
    int playerValue = (playerToken.equals(PositionToken.PLAYER_ONE)) ? 1 : -1;

  }

}
