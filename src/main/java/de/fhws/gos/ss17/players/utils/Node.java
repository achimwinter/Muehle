package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.ss17.game.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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


  public void getTree() throws GameException {
    List<Move> possibleMoves = PossibleMoves.getPossibleMoves(this.board, this.phase, playerToken);
    Random rand = new Random();
    int randomNumber = rand.nextInt(possibleMoves.size());
    Iterator<Move> moveIterator = possibleMoves.iterator();
    while (randomNumber > 0){
      Move move = moveIterator.next();
      Board newboard = new Board(board);
      newboard.executeMove(move, playerToken);
      //Phase herausfinden
      PositionToken nextPlayer = (playerToken.equals(PositionToken.PLAYER_ONE)) ? PositionToken.PLAYER_TWO : PositionToken.PLAYER_ONE;
      subNodes.add(new Node(newboard, this.phase, this.depth -1, nextPlayer));
      --randomNumber;
    }
  }
}