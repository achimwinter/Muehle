package de.fhws.gos.ss17.players.utils;


import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Position;
import de.fhws.gos.ss17.core.utils.PositionToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by awinter on 08.06.17.
 */
public class BoardState {

  public static int getScore(Board board, int playerValue, Phase phase) {
    PositionToken playerToken =
        (playerValue == 1) ? PositionToken.PLAYER_ONE : PositionToken.PLAYER_TWO;
    PositionToken enemyToken =
        (playerValue == 1) ? PositionToken.PLAYER_TWO : PositionToken.PLAYER_ONE;

    int enemyMills = getNumberOfMills(board, enemyToken);
    int playerMills = getNumberOfMills(board, playerToken);
    int diffInMills = playerMills - enemyMills;

    int enemyBlockedPieces = getEnemyBlockedPieces(board, enemyToken);
    int playerBlockedPieces = getEnemyBlockedPieces(board, playerToken);
    int diffinBlockedPieces = playerBlockedPieces - enemyBlockedPieces;

    int diffPieces = board.getNumberOfTokensForPlayer(playerToken) - board
        .getNumberOfTokensForPlayer(enemyToken);

    int diff2PieceConfigs =
        get2PieceConfigs(board, playerToken) - get2PieceConfigs(board, enemyToken);

    int diffDoubleMills = 1;
    try {
      diffDoubleMills =
          getDoubleMills(board, playerToken) - getDoubleMills(board, enemyToken);
    } catch (GameException e) {
      e.printStackTrace();
    }
    //System.out.println("Phasennummer: " + phase.ordinal());
    switch (phase.ordinal()) {
      case 0:
        return 26 * diffInMills + 3 * diffinBlockedPieces + 9 * diffPieces + 10 * diff2PieceConfigs;
      case 1:
        return 43 * diffInMills + 10 * diffinBlockedPieces + 11 * diffPieces + 8 * diff2PieceConfigs
            + 8 * diffDoubleMills;
      case 2:
        return 43 * diffInMills + 10 * diffinBlockedPieces + 11 * diffPieces + 8 * diff2PieceConfigs
            + 8 * diffDoubleMills;
      default:
        return 0;
    }
  }

  public static int getWinningConfig(Board board, PositionToken playerToken) {
    //high value
    return 500;
  }

  public static int getNumberOfMills(Board board, PositionToken playerToken) {
    int counter = 0;
    for (List<Integer> millcombs : MillCombinations.POSSIBLE_MILLS) {
      int minicounter = 0;
      for (int i : millcombs) {
        try {
          if (board.getPosition(i).getPositionToken().equals(playerToken)) {
            minicounter++;
          }
        } catch (GameException e) {
          e.printStackTrace();
        }
      }
      if (minicounter == 3) {
        counter++;
      }
    }

    return counter;
  }


  //work and tests done
  public static int getDoubleMills(Board board, PositionToken playerToken) throws GameException {
    int counter = 0;
    List<List<Integer>> friendlyMillsOnBoard = new ArrayList<>();
    for (List<Integer> millCoords : MillCombinations.POSSIBLE_MILLS) {
      int pieceCounter = 0;
      for (int i : millCoords) {
        if (board.getPosition(i).getPositionToken().equals(playerToken)) {
          pieceCounter++;
        }
      }
      if (pieceCounter == 3) {
        friendlyMillsOnBoard.add(millCoords);
      }
    }
    for (int i = 0; i < 24; ++i) {
      final int ii = i;
      counter += Math.max(0, friendlyMillsOnBoard.stream().filter(x -> x.contains(ii)).count() - 1);
    }
    return counter;
  }

  //work and test pending
  private static int get3PieceConfigs(Board board, PositionToken playerToken) {
    Iterator<Position> positionIterator = board.iteratePositions();
    while (positionIterator.hasNext()) {
      List<Integer> millCombWithStone = new ArrayList<>();
      Position position = positionIterator.next();
      if (!position.getPositionToken().equals(playerToken)) {
        continue;
      }

      int positionCounter = 0;
      for (List<Integer> millComb : MillCombinations.POSSIBLE_MILLS) {
        if (millComb.contains(position)) {

        }
      }


    }
    return 1;
  }

  //work finished and test passed
  public static int get2PieceConfigs(Board board, PositionToken playerToken) {
    int possible2Mills = 0;
    for (List<Integer> millComb : MillCombinations.POSSIBLE_MILLS) {
      int counter = 0;
      int oneAvailable = 0;
      try {
        for (int i : millComb) {
          if (board.getPosition(i).getPositionToken() == playerToken) {
            counter++;
          }
          if (board.getPosition(i).isAvailable()) {
            oneAvailable = 1;
          }
        }
      } catch (GameException ex) {
        ex.printStackTrace();
      }
      if (counter == 2 && oneAvailable == 1) {
        possible2Mills++;
      }
    }
    return possible2Mills;
  }

  //work finished and test passed
  public static int getEnemyBlockedPieces(Board board, PositionToken playerToken) {
    int blockedPieces = 0;
    Iterator<Position> positionIterator = board.iteratePositions();
    while (positionIterator.hasNext()) {
      Position position = positionIterator.next();
      if (position.getPositionToken() == playerToken) {
        continue;
      }
      Position[] neighbours = position.getNeighbors();
      boolean noFreeNeighbour = true;
      for (Position neighbour : neighbours) {
        if (neighbour.isAvailable()) {
          noFreeNeighbour = false;
        }
      }
      if (noFreeNeighbour) {
        blockedPieces++;
      }
    }
    return blockedPieces;
  }

}
