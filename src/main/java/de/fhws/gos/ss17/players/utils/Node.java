package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.utils.GameStatus;
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

  public int boardValue;
  public Board board;
  private Move move;

  public Move getMove() {
    return move;
  }

  public Node(Board board, Move move) {
    this.board = board;
    this.move = move;
  }

/*
  public void getTree() throws GameException {
    List<Move> possibleMoves = PossibleMoves.getPossibleMoves(this.board, this.phase, playerToken);
    Random rand = new Random();
    int randomNumber = rand.nextInt(possibleMoves.size());
    Iterator<Move> moveIterator = possibleMoves.iterator();
    while (moveIterator.hasNext()) {
      Move move = moveIterator.next();
      Board newboard = new Board(board);
      newboard.executeMove(move, playerToken);
      newboard.printBoard();
      board.printBoard();
      //Phase herausfinden
      PositionToken nextPlayer =
          (playerToken.equals(PositionToken.PLAYER_ONE)) ? PositionToken.PLAYER_TWO
              : PositionToken.PLAYER_ONE;
      subNodes.add(new Node(newboard, this.phase, this.depth - 1, nextPlayer));
      --randomNumber;
    }
  }
  */
}