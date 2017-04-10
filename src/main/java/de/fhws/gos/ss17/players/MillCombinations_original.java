package de.fhws.gos.ss17.players;
/*
import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
//import de.fhws.gos.players.utils.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
*/
/**
 * Created by Auron on 07.04.2017.
 */
/*
public class MillCombinations_original {
  private static de.fhws.gos.players.utils.MillCombinations instance;
  private Board board;
  private List<Position[]> millCombinations;
  private List<Integer[]> millCombinationIndices;

  public static de.fhws.gos.players.utils.MillCombination getInstance(Board board) throws GameException {
    if(instance == null) {
      instance = new MillCombinations_original(board);
    }

    return instance;
  }

  private MillCombinations(Board board) throws GameException {
    this.board = board;
    this.initMillCombinations();
    this.initMillCombinationIndices();
  }

  private void initMillCombinations() throws GameException {
    this.millCombinations = new LinkedList();
    this.millCombinations.add(0, new Position[]{this.board.getPosition(0), this.board.getPosition(1), this.board.getPosition(2)});
    this.millCombinations.add(1, new Position[]{this.board.getPosition(0), this.board.getPosition(9), this.board.getPosition(21)});
    this.millCombinations.add(2, new Position[]{this.board.getPosition(2), this.board.getPosition(14), this.board.getPosition(23)});
    this.millCombinations.add(3, new Position[]{this.board.getPosition(21), this.board.getPosition(22), this.board.getPosition(23)});
    this.millCombinations.add(4, new Position[]{this.board.getPosition(3), this.board.getPosition(4), this.board.getPosition(5)});
    this.millCombinations.add(5, new Position[]{this.board.getPosition(3), this.board.getPosition(10), this.board.getPosition(18)});
    this.millCombinations.add(6, new Position[]{this.board.getPosition(5), this.board.getPosition(13), this.board.getPosition(20)});
    this.millCombinations.add(7, new Position[]{this.board.getPosition(18), this.board.getPosition(19), this.board.getPosition(20)});
    this.millCombinations.add(8, new Position[]{this.board.getPosition(6), this.board.getPosition(7), this.board.getPosition(8)});
    this.millCombinations.add(9, new Position[]{this.board.getPosition(6), this.board.getPosition(11), this.board.getPosition(15)});
    this.millCombinations.add(10, new Position[]{this.board.getPosition(8), this.board.getPosition(12), this.board.getPosition(17)});
    this.millCombinations.add(11, new Position[]{this.board.getPosition(15), this.board.getPosition(16), this.board.getPosition(17)});
    this.millCombinations.add(12, new Position[]{this.board.getPosition(1), this.board.getPosition(4), this.board.getPosition(7)});
    this.millCombinations.add(13, new Position[]{this.board.getPosition(9), this.board.getPosition(10), this.board.getPosition(11)});
    this.millCombinations.add(14, new Position[]{this.board.getPosition(12), this.board.getPosition(13), this.board.getPosition(14)});
    this.millCombinations.add(15, new Position[]{this.board.getPosition(16), this.board.getPosition(19), this.board.getPosition(22)});
  }

  private void initMillCombinationIndices() {
    this.millCombinationIndices = new LinkedList();
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(0), Integer.valueOf(1)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(0), Integer.valueOf(12)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(0), Integer.valueOf(2)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(4), Integer.valueOf(5)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(4), Integer.valueOf(12)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(4), Integer.valueOf(6)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(8), Integer.valueOf(9)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(8), Integer.valueOf(12)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(8), Integer.valueOf(10)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(1), Integer.valueOf(13)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(5), Integer.valueOf(13)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(9), Integer.valueOf(13)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(10), Integer.valueOf(14)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(6), Integer.valueOf(14)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(2), Integer.valueOf(14)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(9), Integer.valueOf(11)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(11), Integer.valueOf(15)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(10), Integer.valueOf(11)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(5), Integer.valueOf(7)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(7), Integer.valueOf(15)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(6), Integer.valueOf(7)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(1), Integer.valueOf(3)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(3), Integer.valueOf(15)});
    this.millCombinationIndices.add(new Integer[]{Integer.valueOf(2), Integer.valueOf(3)});
  }

  public Integer[] getMillCombinationIndicesFor(int positionIndex) {
    return (Integer[])this.millCombinationIndices.get(positionIndex);
  }

  public List<Position[]> getMillCombinationsFor(int positionIndex) {
    List<Position[]> mills = new LinkedList();
    Integer[] indices = this.getMillCombinationIndicesFor(positionIndex);
    mills.add(this.millCombinations.get(indices[0].intValue()));
    mills.add(this.millCombinations.get(indices[1].intValue()));
    return mills;
  }

  public boolean isMill(PositionToken token, int toId) {
    return this.isMill(token, -1, toId);
  }

  public boolean isMill(PositionToken token, int fromId, int toId) {
    List<Position[]> millCombinations = this.getMillCombinationsFor(toId);
    return this.checkMill((Position[])millCombinations.get(0), token, fromId, toId) || this.checkMill((Position[])millCombinations.get(1), token, fromId, toId);
  }

  private boolean checkMill(Position[] millPositions, PositionToken token, int fromId, int toId) {
    boolean result = true;
    Position[] var6 = millPositions;
    int var7 = millPositions.length;

    for(int var8 = 0; var8 < var7; ++var8) {
      Position millPosition = var6[var8];
      if(millPosition.getId() == toId) {
        result = result && (millPosition.getPositionToken().equals(PositionToken.IS_EMPTY) || millPosition.getPositionToken().equals(token));
      } else {
        result = result && millPosition.getPositionToken().equals(token) && millPosition.getId() != fromId;
      }
    }

    return result;
  }

  public boolean allInMill(PositionToken token) {
    int tokensForPlayer = this.board.getNumberOfTokensForPlayer(token);
    int tokenInMillCounter = 0;
    Iterator iterator = this.board.iteratePositions();

    while(iterator.hasNext()) {
      Position pos = (Position)iterator.next();
      if(this.isMill(token, pos.getId())) {
        ++tokenInMillCounter;
      }
    }

    return tokenInMillCounter == tokensForPlayer;
  }

}
*/