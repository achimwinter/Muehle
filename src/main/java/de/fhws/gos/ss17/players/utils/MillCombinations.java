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

  private static MillCombinations instance;
  private Board board;

  public static MillCombinations getInstance(Board board) {
    if (instance == null) {
      instance = new MillCombinations(board);
    }
    return instance;
  }

  private MillCombinations(Board board) {
    this.board = board;
  }

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


  public boolean isMill(PositionToken playerToken, int fromId, int toId) throws GameException {
    return isMill(playerToken, toId);
  }


  private boolean isAllied(List<Integer> millcoords, PositionToken playerToken) throws GameException {
    int counter = 0;
    for (int i : millcoords) {
      if (board.getPosition(i).getPositionToken() == PositionToken.PLAYER_TWO) {
        return false;
      } else if (board.getPosition(i).getPositionToken() == playerToken) {
        ++counter;
      }
    }
    return counter == 2;
  }

  public boolean willBeMill(PositionToken playerToken, int fromId, int toId) throws GameException {
    for (List<Integer> millCoords : POSSIBLE_MILLS) {
      if (millCoords.contains(toId)) {
        boolean result = true;
        for (int i : millCoords) {
          if (i == fromId) {
            result = false;
          } else if (result) {
            if (!isAllied(millCoords, playerToken)) {
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


  public Integer[][] getMillCombinations(int positionIndex) {
    int counter = 0;
    Integer[][] combinations = new Integer[2][3];
    for (int i = 0; i < possibleMillsArray.length; i++) {
      for (int j = 0; j < 3; j++) {
        if (positionIndex == possibleMillsArray[i][j]) {
          combinations[counter] = possibleMillsArray[i];
          counter++;
        }
      }
    }
    return combinations;
  }
/*
  public boolean willBeMill(PositionToken playerToken, int fromId, int toId) throws GameException {
    Integer[][] combinations = getMillCombinations(toId);
    for (int i = 0; i < 2; i++) {
      if (board.getPosition(combinations[i][0]).getPositionToken().equals(playerToken) && board
            .getPosition(combinations[i][1]).getPositionToken().equals(playerToken) && board
            .getPosition(combinations[i][2]).getPositionToken().equals(playerToken)
            ||
            board.getPosition(toId).isAvailable() &&
                (board.getPosition(combinations[i][1]).getPositionToken().equals(playerToken) &&
                    fromId != combinations[i][1]) &&
                (board.getPosition(combinations[i][2]).getPositionToken().equals(playerToken) &&
                    fromId != combinations[i][2])
            ||
            (board.getPosition(combinations[i][0]).getPositionToken().equals(playerToken)
                && fromId != combinations[i][0]) && board
                .getPosition(toId).isAvailable() &&
                (board.getPosition(combinations[i][2]).getPositionToken().equals(playerToken)
                    && fromId != combinations[i][2])
            ||
            (board.getPosition(combinations[i][0]).getPositionToken().equals(playerToken)
                && fromId != combinations[i][0]) &&
                (board.getPosition(combinations[i][1]).getPositionToken().equals(playerToken)
                    && fromId != combinations[i][1])
                && board.getPosition(toId).isAvailable()) {
          return true;
      }
    }
    return false;
  }
*/

  public boolean isMill(PositionToken playerToken, int stoneId) throws GameException {
    Integer[][] combinations = getMillCombinations(stoneId);
    for (int i = 0; i < 2; i++) {
      if (board.getPosition(combinations[i][0]).getPositionToken().equals(playerToken) && board
          .getPosition(combinations[i][1]).getPositionToken().equals(playerToken) && board
          .getPosition(combinations[i][2]).getPositionToken().equals(playerToken)) {
        return true;
      }
    }
    return false;
  }

  public boolean allInMill(PositionToken token) throws GameException {
    int tokensForPlayer = this.board.getNumberOfTokensForPlayer(token);
    int tokenInMillCounter = 0;
    Iterator iterator = this.board.iteratePositions();

    while (iterator.hasNext()) {
      Position pos = (Position) iterator.next();
      if (this.isMill(token, pos.getId())) {
        ++tokenInMillCounter;
      }
    }

    return tokenInMillCounter == tokensForPlayer;
  }


  static {
    List<List<Integer>> possMills = new ArrayList<List<Integer>>();

    for (int i = 0; i < possibleMillsArray.length; i++) {
      possMills.add(Collections.unmodifiableList(Arrays.asList(possibleMillsArray[i])));
    }

    POSSIBLE_MILLS = Collections.unmodifiableList(possMills);
  }
}