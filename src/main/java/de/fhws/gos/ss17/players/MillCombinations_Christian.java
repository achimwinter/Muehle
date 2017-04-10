package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Auron on 05.04.2017.
 */
/*
public class MillCombinations_Christian {
  private static MillCombinations_Christian instance;

  private Board board;

  private List<Position[]> MillCombinations_Christian;
  private List<Integer[]> millCombinationIndices;

  public static MillCombinations_Christian getInstance(Board board) throws GameException {
    if(instance == null) {
      instance = new MillCombinations_Christian(board);
    }

    return instance;
  }
*/
/*
  private MillCombinations_Christian(Board board) throws GameException {
    this.board = board;
    this.initMillCombinations_Christian_senkrecht();
    this.initMillCombinations_Christian_senkrecht();
    this.initMillCombinations_Christian();
  }
*/
/*
  private Position[] initMillCombinations_Christian_waagerecht(int token) throws GameException{
    Position[][] millposition = {
        {this.board.getPosition(0),this.board.getPosition(1),this.board.getPosition(2)},
        {this.board.getPosition(0),this.board.getPosition(1),this.board.getPosition(2)},
        {this.board.getPosition(0),this.board.getPosition(1),this.board.getPosition(2)},
        {this.board.getPosition(3),this.board.getPosition(4),this.board.getPosition(5)},
        {this.board.getPosition(3),this.board.getPosition(4),this.board.getPosition(5)},
        {this.board.getPosition(3),this.board.getPosition(4),this.board.getPosition(5)},
        {this.board.getPosition(6),this.board.getPosition(7),this.board.getPosition(8)},
        {this.board.getPosition(6),this.board.getPosition(7),this.board.getPosition(8)},
        {this.board.getPosition(6),this.board.getPosition(7),this.board.getPosition(8)},
        {this.board.getPosition(9),this.board.getPosition(10),this.board.getPosition(11)},
        {this.board.getPosition(9),this.board.getPosition(10),this.board.getPosition(11)},
        {this.board.getPosition(9),this.board.getPosition(10),this.board.getPosition(11)},
        {this.board.getPosition(12),this.board.getPosition(13),this.board.getPosition(14)},
        {this.board.getPosition(12),this.board.getPosition(13),this.board.getPosition(14)},
        {this.board.getPosition(12),this.board.getPosition(13),this.board.getPosition(14)},
        {this.board.getPosition(15),this.board.getPosition(16),this.board.getPosition(17)},
        {this.board.getPosition(15),this.board.getPosition(16),this.board.getPosition(17)},
        {this.board.getPosition(15),this.board.getPosition(16),this.board.getPosition(17)},
        {this.board.getPosition(18),this.board.getPosition(19),this.board.getPosition(20)},
        {this.board.getPosition(18),this.board.getPosition(19),this.board.getPosition(20)},
        {this.board.getPosition(18),this.board.getPosition(19),this.board.getPosition(20)},
        {this.board.getPosition(21),this.board.getPosition(22),this.board.getPosition(23)},
        {this.board.getPosition(21),this.board.getPosition(22),this.board.getPosition(23)},
        {this.board.getPosition(21),this.board.getPosition(22),this.board.getPosition(23)},
    };
    return millposition[token];
  }

  private Position[] initMillCombinations_Christian_senkrecht(int token) throws GameException{
    Position[][] millposition = {
        {this.board.getPosition(0),this.board.getPosition(9),this.board.getPosition(21)},
        {this.board.getPosition(1),this.board.getPosition(4),this.board.getPosition(7)},
        {this.board.getPosition(2),this.board.getPosition(14),this.board.getPosition(23)},
        {this.board.getPosition(3),this.board.getPosition(10),this.board.getPosition(18)},
        {this.board.getPosition(1),this.board.getPosition(4),this.board.getPosition(7)},
        {this.board.getPosition(5),this.board.getPosition(13),this.board.getPosition(20)},
        {this.board.getPosition(6),this.board.getPosition(11),this.board.getPosition(15)},
        {this.board.getPosition(1),this.board.getPosition(4),this.board.getPosition(7)},
        {this.board.getPosition(8),this.board.getPosition(12),this.board.getPosition(17)},
        {this.board.getPosition(0),this.board.getPosition(9),this.board.getPosition(21)},
        {this.board.getPosition(3),this.board.getPosition(10),this.board.getPosition(18)},
        {this.board.getPosition(6),this.board.getPosition(11),this.board.getPosition(15)},
        {this.board.getPosition(8),this.board.getPosition(12),this.board.getPosition(17)},
        {this.board.getPosition(5),this.board.getPosition(13),this.board.getPosition(20)},
        {this.board.getPosition(2),this.board.getPosition(14),this.board.getPosition(23)},
        {this.board.getPosition(6),this.board.getPosition(11),this.board.getPosition(15)},
        {this.board.getPosition(16),this.board.getPosition(19),this.board.getPosition(22)},
        {this.board.getPosition(8),this.board.getPosition(12),this.board.getPosition(17)},
        {this.board.getPosition(3),this.board.getPosition(10),this.board.getPosition(18)},
        {this.board.getPosition(16),this.board.getPosition(19),this.board.getPosition(22)},
        {this.board.getPosition(5),this.board.getPosition(13),this.board.getPosition(20)},
        {this.board.getPosition(0),this.board.getPosition(9),this.board.getPosition(21)},
        {this.board.getPosition(16),this.board.getPosition(19),this.board.getPosition(22)},
        {this.board.getPosition(2),this.board.getPosition(14),this.board.getPosition(23)}
    };
    return millposition[token];
  }
  */
/*
  private Position[][] initMillCombinations_Christian() throws GameException {
    Position[][] millposition = {
        {this.board.getPosition(0),this.board.getPosition(1),this.board.getPosition(2)},
        {this.board.getPosition(3),this.board.getPosition(4),this.board.getPosition(5)},
        {this.board.getPosition(6),this.board.getPosition(7),this.board.getPosition(8)},
        {this.board.getPosition(9),this.board.getPosition(10),this.board.getPosition(11)},
        {this.board.getPosition(12),this.board.getPosition(13),this.board.getPosition(14)},
        {this.board.getPosition(15),this.board.getPosition(16),this.board.getPosition(17)},
        {this.board.getPosition(18),this.board.getPosition(19),this.board.getPosition(20)},
        {this.board.getPosition(21),this.board.getPosition(22),this.board.getPosition(23)},
        {this.board.getPosition(0),this.board.getPosition(9),this.board.getPosition(21)},
        {this.board.getPosition(3),this.board.getPosition(10),this.board.getPosition(18)},
        {this.board.getPosition(6),this.board.getPosition(11),this.board.getPosition(15)},
        {this.board.getPosition(1),this.board.getPosition(4),this.board.getPosition(7)},
        {this.board.getPosition(16),this.board.getPosition(19),this.board.getPosition(22)},
        {this.board.getPosition(8),this.board.getPosition(12),this.board.getPosition(17)},
        {this.board.getPosition(5),this.board.getPosition(13),this.board.getPosition(20)},
        {this.board.getPosition(2),this.board.getPosition(14),this.board.getPosition(23)}
    };
    return millposition;

  }
*/
 /* private int[][] initMillCombinationIndices(){
    int[][] mills = {
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {9,10,11},
        {12,13,14},
        {15,16,17},
        {18,19,20},
        {21,22,23},
        {0,9,21},
        {3,10,18},
        {6,11,15},
        {1,4,7},
        {16,19,22},
        {8,12,17},
        {5,13,20},
        {2,14,23}
    };

    return mills;
  }
*/
 /*
  public List<Position[]> getMillCombinations_ChristianFor(int positionIndex) throws GameException {
    List<Position[]> mills = new LinkedList();
    mills.add(initMillCombinations_Christian_senkrecht(positionIndex));
    mills.add(initMillCombinations_Christian_waagerecht(positionIndex));
    return mills;
  }

  public boolean isMill(PositionToken token, int toId) throws GameException {
    return this.isMill(token, -1, toId);
  }

  public boolean isMill(PositionToken token, int fromId, int toId) throws GameException {
    List<Position[]> MillCombinations_Christian = this.getMillCombinations_ChristianFor(toId);
    return this.checkMill(MillCombinations_Christian.get(0), token, fromId, toId) || this.checkMill(MillCombinations_Christian.get(1), token, fromId, toId);
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

  public boolean allInMill(PositionToken token) throws GameException {
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


  /*

  private boolean checkMill(Position[] millPositions, PositionToken token, int toId) throws GameException {
    boolean bool = false;
    Position[] mills = millPositions;
    for(int i = 0; i < 16; i++){
      for (int j=0; j< 3; j++){
        Position millPosition = mills[j];
        if(initMillCombinationIndices()[i][j] == toId){
          if(j==0){bool = bool && (millPosition.getPositionToken().equals(PositionToken.IS_EMPTY) || millPosition.getPositionToken().equals(token));}
          if(j==1){bool = bool && (millPosition.getPositionToken().equals(PositionToken.IS_EMPTY) || millPosition.getPositionToken().equals(token));}
          if(j==2){bool = bool && (millPosition.getPositionToken().equals(PositionToken.IS_EMPTY) || millPosition.getPositionToken().equals(token));}
        }
      }
    }

    return bool;
  }

  public boolean isMill(PositionToken token, int toId) {
    return this.isMill(token, -1, toId);
  }

  public boolean isMill(PositionToken token, int fromId, int toId) {
    List<Position[]> MillCombinations_Christian = this.getMillCombinations_ChristianFor(toId);
    return this.checkMill((Position[])MillCombinations_Christian.get(0), token, fromId, toId) || this.checkMill((Position[])MillCombinations_Christian.get(1), token, fromId, toId);
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
  */
