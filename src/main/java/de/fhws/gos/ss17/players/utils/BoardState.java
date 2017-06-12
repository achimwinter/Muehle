package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.utils.PositionToken;
import java.util.List;

/**
 * Created by awinter on 08.06.17.
 */
public class BoardState {

  public static int getScore(Board board){
    return 5;
  }


  private static int getMills(Board board, PositionToken playerToken){
    int counter = 0;
    for(List<Integer> millcombs : MillCombinations.POSSIBLE_MILLS){
      int minicounter = 0;
          for(int i : millcombs){
            try {
              if(board.getPosition(i).getPositionToken().equals(playerToken))
                minicounter++;
            } catch (GameException e) {
              e.printStackTrace();
            }
          }
          if(minicounter==3)
            counter++;
    }

  return counter;
  }


}
