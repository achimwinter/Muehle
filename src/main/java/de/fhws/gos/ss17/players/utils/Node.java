package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;

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

  public int getBoardValue() {
    return boardValue;
  }

  public void setBoardValue(int boardValue) {
    this.boardValue = boardValue;
  }
}