package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by awinter on 08.06.17.
 */
public class BoardState {

  public static int getScore(Board board, int playerValue) {
    PositionToken playerToken =
        (playerValue == 1) ? PositionToken.PLAYER_ONE : PositionToken.PLAYER_TWO;
    PositionToken enemyToken =
        (playerValue == 1) ? PositionToken.PLAYER_TWO : PositionToken.PLAYER_ONE;

    int enemyMills = getNumberOfMills(board, enemyToken);
    int playerMills = getNumberOfMills(board, playerToken);
    int diffInMills = playerMills - enemyMills;

    int enemyBlockedPieces = getBlockedPieces(board, enemyToken);
    int playerBlockedPieces = getBlockedPieces(board, playerToken);
    int diffinBlockedPieces = playerBlockedPieces - enemyBlockedPieces;

    int diffPieces = board.getNumberOfTokensForPlayer(playerToken) - board
        .getNumberOfTokensForPlayer(enemyToken);

    int diff2PieceConfigs = get2PieceConfigs(board, playerToken) - get2PieceConfigs(board, enemyToken);


    return 0;
  }

  public static int getWinningConfig(Board board, PositionToken playerToken){
    //high value
    return 500;
  }

  public static int getDoubleMills(Board board, PositionToken playerToken) {
    int counter = 0;
    Iterator<Position> positionIterator = board.iteratePositions();
    while (positionIterator.hasNext()) {
      int positionCounter = 0;
      Position position = positionIterator.next();
      if (position.getPositionToken() != playerToken) {
        continue;
      }


      for (List<Integer> millcomb : MillCombinations.POSSIBLE_MILLS) {
        if (millcomb.contains(position.getId()) && MillCombinations
            .isMill(board, playerToken, position.getId())) {
          positionCounter++;
        }
      }
      if(positionCounter == 2)
        ++counter;

    }
    return counter;
  }


  private static int get3PieceConfigs(Board board, PositionToken playerToken){
    Iterator<Position> positionIterator = board.iteratePositions();
    while(positionIterator.hasNext()){
      List<Integer> millCombWithStone = new ArrayList<>();
      Position position = positionIterator.next();
      if (!position.getPositionToken().equals(playerToken))
        continue;

      int positionCounter = 0;
      for(List<Integer> millComb : MillCombinations.POSSIBLE_MILLS){
        if(millComb.contains(position)){

        }
      }




    }
    return 0;
  }

  private static int get2PieceConfigs(Board board, PositionToken playerToken) {
    int possible2Mills = 0;
    for (List<Integer> millComb : MillCombinations.POSSIBLE_MILLS) {
      int counter = 0;
      int oneAvailable = 0;
      try {
        for (int i : millComb) {
          if (board.getPosition(i).getPositionToken() == playerToken) {
            counter++;
          }
          if (board.getPosition(i).isAvailable())
            oneAvailable = 1;
        }
      }catch (GameException ex){
        ex.printStackTrace();
      }
      if(counter == 2 && oneAvailable == 1)
        possible2Mills++;
    }
    return possible2Mills;
  }


  private static int getBlockedPieces(Board board, PositionToken playerToken) {
    int blockedPieces = 0;
    Iterator<Position> positionIterator = board.iteratePositions();
    while (positionIterator.hasNext()) {
      Position position = positionIterator.next();
      if (position.getPositionToken() != playerToken) {
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

  private static int getNumberOfMills(Board board, PositionToken playerToken) {
    int counter = 0;
    Iterator<Position> positionIterator = board.iteratePositions();
    while (positionIterator.hasNext()) {
      Position position = positionIterator.next();
      if (MillCombinations.isMill(board, playerToken, position.getId())) {
        counter++;
      }
    }
    return counter;
  }
}
