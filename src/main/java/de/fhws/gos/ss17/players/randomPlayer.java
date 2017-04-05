import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.players.impl.AbstractPlayer;
import de.fhws.gos.ss17.logic.impl.RulesImpl;

public class randomPlayer extends AbstractPlayer {

  RulesImpl rules = new RulesImpl();
  public RandomPlayer (PositionToken playerToken) {super(playerToken)};

  protected Move getNextMove(board){
    return this.move;
  }
  protected Move getPlacingMove(Board board) throws GameException {
    int randomValue;
    int removeId = -1;

    System.out.print("ID des zu setzenden Steines eingeben: ");
    int toId;

    do {
      randomValue = Math.random(*24);
    } while (!board.getPosition(toId).isAvailable());

    if (rules.willBeMill(board)) {
      System.out.print("ID des zu entfernenden Steines eingeben: ");
      removeId = this.getRemoveIndex(board);
    }
    return new Move(-1, toId, removeId);
  }

}