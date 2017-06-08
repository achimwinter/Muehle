package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Achim on 01.06.2017.
 */
public class Node {

  private Board board;
  private Phase phase;
  private int depth;
  private PositionToken playerToken;
  private List<Node> subNodes = new ArrayList<>();

  public Node(Board board, Phase phase, int depth, PositionToken playerToken){
    this.board = board;
    this.phase = phase;
    this.depth = depth;
    this.playerToken = playerToken;
  }

  //generate possible Moves
  //getBoardScore
  //Copy Board


  public Node getTree() throws GameException {
    List<Move> possibleMoves = PossibleMoves.getPossibleMoves(this.board, this.phase);
    Iterator<Move> moveIterator = possibleMoves.iterator();
    while (moveIterator.hasNext()){
      Move move = moveIterator.next();
      Board newboard = this.board;
      newboard.executeMove(move, playerToken);
      //Wie finde ich die Phase heraus?
      PositionToken nextPlayer = (playerToken.equals(PositionToken.PLAYER_ONE)) ? PositionToken.PLAYER_TWO : PositionToken.PLAYER_ONE;
      subNodes.add(new Node(newboard, this.phase, this.depth -1, nextPlayer));
    }
    return this;
  }
}