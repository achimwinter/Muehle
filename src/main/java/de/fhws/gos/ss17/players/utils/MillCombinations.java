package de.fhws.gos.ss17.players.utils;

/**
 * Created by Achim on 09.04.2017.
 */

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MillCombinations {

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


  public static boolean isMill(Board board, PositionToken playerToken, int fromId , int toId) throws GameException {
    return isMill(board, playerToken, toId);
  }

  private static boolean isAllied(Board board, List<Integer> millcoords, PositionToken playerToken) {
    int counter = 0;
    try {
      for (int i : millcoords) {
        if (board.getPosition(i).getPositionToken() == PositionToken.PLAYER_TWO) {
          return false;
        } else if (board.getPosition(i).getPositionToken() == playerToken) {
          ++counter;
        }
      }
    }catch (GameException ex){
      System.out.println("GAME EXCEPTION IN isAllied IN MILLCOMBINATIONS");
      ex.printStackTrace();
    }
    return counter == 2;
  }

  public static boolean willBeMill(Board board, PositionToken playerToken, int fromId, int toId) {
    for (List<Integer> millCoords : POSSIBLE_MILLS) {
      if (millCoords.contains(toId)) {
        boolean result = true;
        for (int i : millCoords) {
          if (i == fromId) {
            result = false;
          } else if (result) {
            if (!isAllied(board , millCoords, playerToken)) {
              result = false;
            }
          }
        }
        if (result) {
          return true;
        }
      }
    }
    return false;
  }


  private static Integer[][] getMillCombinations(int positionIndex) {
    int counter = 0;
    Integer[][] combinations = new Integer[2][3];
    for (Integer[] aPossibleMillsArray : possibleMillsArray) {
      for (int j = 0; j < 3; j++) {
        if (positionIndex == aPossibleMillsArray[j]) {
          combinations[counter] = aPossibleMillsArray;
          counter++;
        }
      }
    }
    return combinations;
  }


  public static boolean isMill(Board board, PositionToken playerToken, int stoneId) throws GameException {
    for (List<Integer> millcomb : POSSIBLE_MILLS){
      int counter = 0;
      for (int i: millcomb) {
        if(board.getPosition(i).getPositionToken().equals(playerToken))
          counter++;
      }
      if(counter == 3)
        return true;
    }
    return false;
  }

  public static boolean allInMill(Board board, PositionToken token) throws GameException {
    int tokensForPlayer = board.getNumberOfTokensForPlayer(token);
    int tokenInMillCounter = 0;
    Iterator iterator = board.iteratePositions();

    while (iterator.hasNext()) {
      Position pos = (Position) iterator.next();
      if (isMill(board, token, pos.getId())) {
        ++tokenInMillCounter;
      }
    }

    return tokenInMillCounter == tokensForPlayer;
  }


  static {
    List<List<Integer>> possMills = new ArrayList<>();

    for (Integer[] aPossibleMillsArray : possibleMillsArray) {
      possMills.add(Collections.unmodifiableList(Arrays.asList(aPossibleMillsArray)));
    }

    POSSIBLE_MILLS = Collections.unmodifiableList(possMills);
  }
}