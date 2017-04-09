package de.fhws.gos.ss17.players;

/**
 * Created by Achim on 09.04.2017.
 */
import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.utils.PositionToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MillCombinations_Achim {
  
  private static MillCombinations_Achim instance;
  private Board board;
  
  public static MillCombinations_Achim getInstance(Board board){
    if (instance == null){
      instance = new MillCombinations_Achim();
    }
    return instance;
  }

  public static final List<List<Integer>> POSITION_TO_NEIGHBOURS;
  private static final Integer[][] positionToNeighboursArray = {
      {1, 9},
      {0, 2, 4},
      {1, 14},
      {4, 10},
      {1, 3, 5, 7},
      {4, 13},
      {7, 11},
      {4, 6, 8},
      {7, 12},
      {0, 10, 21},
      {3, 9, 11, 18},
      {6, 10, 15},
      {8, 13, 17},
      {5, 12, 14, 20},
      {2, 13, 23},
      {11, 16},
      {15, 17, 19},
      {12, 16},
      {10, 19},
      {16, 18, 20, 22},
      {13, 19},
      {9, 22},
      {19, 21, 23},
      {14, 22},
  };
  public static final List<List<Integer>> POSSIBLE_MILLS;
  private static final Integer[][] possibleMillsArray = {
      {0, 1, 2},
      {3, 4, 5},
      {6, 7, 8},
      {9, 10, 11},
      {12, 13, 14},
      {15, 16, 17},
      {18, 19, 20},
      {21, 22, 23},
      {0, 9, 21},
      {3, 10, 18},
      {6, 11, 15},
      {1, 4, 7},
      {16, 19, 22},
      {8, 12, 17},
      {5, 13, 20},
      {2, 14, 23},
  };

  public Integer[][] getMillCombinations(int positionIndex){
    int counter = 0;
    Integer[][] combinations = new Integer[2][3];
    for(int i = 0; i<POSSIBLE_MILLS.toArray().length ; i++){
      for (int j = 0; j < 3; j++){
        if(positionIndex == possibleMillsArray[i][j]){
          combinations[counter] = possibleMillsArray[i];
          counter++;
        }
      }
    }
    return combinations;
  }

  public boolean isMill(PositionToken playerToken, int fromId, int toId) throws GameException {
    return isMill(playerToken, toId);
  }

  public boolean isMill(PositionToken playerToken, int stoneId) throws GameException {
    Integer[][] combinations = getMillCombinations(stoneId);
    for(int i = 0; i<2; i++){
      if(board.getPosition(combinations[i][0]).equals(playerToken) && board.getPosition(combinations[i][1]).equals(playerToken) && board.getPosition(combinations[i][2]).equals(playerToken)){
        return true;
      }
    }
    return false;
  }
  
  

  static {
    List<List<Integer>> posToNeigh = new ArrayList<List<Integer>>();

    for (int i = 0; i < positionToNeighboursArray.length; i++) {
      posToNeigh.add(Collections.unmodifiableList(Arrays.asList(positionToNeighboursArray[i])));
    }

    POSITION_TO_NEIGHBOURS = Collections.unmodifiableList(posToNeigh);

    List<List<Integer>> possMills = new ArrayList<List<Integer>>();

    for (int i = 0; i < possibleMillsArray.length; i++) {
      possMills.add(Collections.unmodifiableList(Arrays.asList(possibleMillsArray[i])));
    }

    POSSIBLE_MILLS = Collections.unmodifiableList(possMills);
  }
}