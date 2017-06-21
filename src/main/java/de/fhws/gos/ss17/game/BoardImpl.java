package de.fhws.gos.ss17.game;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.logic.Position;
import de.fhws.gos.ss17.core.utils.GameStatus;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by Neuer on 03.05.2017.
 */
public class BoardImpl implements Board {

  private PositionImpl[] positionImpl;
  private GameStatus gameStatus;
  private int[] tokenCounters;
  private int[] moveCounters;
  private LinkedList<Move> moveHistory;

  public BoardImpl() {
    this.gameStatus = GameStatus.RUNNING;
    this.tokenCounters = new int[2];
    this.tokenCounters[0] = 0;
    this.tokenCounters[1] = 0;
    this.moveCounters = new int[2];
    this.moveCounters[0] = 0;
    this.moveCounters[1] = 0;
    this.initPositions();
    this.moveHistory = new LinkedList<>();
  }


  public PositionImpl[] getpositionImpl() {
    return positionImpl;
  }

  public void setpositionImpl(PositionImpl[] positionImpl) {
    this.positionImpl = positionImpl;
  }

  @Override
  public void executeMove(Move move, PositionToken positionToken) {
    this.movePlusPlus(positionToken);
    this.moveHistory.add(move);
    if (move.getFromId() != -1) {
      System.out.println(positionToken + " FR " + move.getFromId());
      this.tokenMinusMinus(this.positionImpl[move.getFromId()].getPositionToken());
      this.positionImpl[move.getFromId()].setPositionToken(PositionToken.IS_EMPTY);
    }

    if (move.getToId() != -1) {
      System.out.println(positionToken + " TO " + move.getToId());
      this.tokenPlusPlus(positionToken);
      this.positionImpl[move.getToId()].setPositionToken(positionToken);
    }

    if (move.getRemoveId() != -1) {
      System.out.println(positionToken + " REM: " + move.getRemoveId());
      this.tokenMinusMinus(this.positionImpl[move.getRemoveId()].getPositionToken());
      this.positionImpl[move.getRemoveId()].setPositionToken(PositionToken.IS_EMPTY);
    }

  }

  @Override
  public Move getLastMove() {
    try {
      return this.moveHistory.getLast();
    } catch (NoSuchElementException ex) {
      return null;
    }

  }

  @Override
  public PositionImpl getPosition(int i) throws GameException {
    if (i >= 0 && i < 24) {
      return this.positionImpl[i];
    } else {
      throw new GameException("Blödl... " + i + "geht net!");
    }
  }

  @Override
  public int getNumberOfTokensForPlayer(PositionToken positionToken) {
    if (positionToken == PositionToken.PLAYER_ONE) {
      return tokenCounters[0];
    }
    if (positionToken == PositionToken.PLAYER_TWO) {
      return tokenCounters[1];
    } else {
      return -1;
    }
  }

  @Override
  public int getNumberOfMovesForPlayer(PositionToken positionToken) {
    if (positionToken == PositionToken.PLAYER_ONE) {
      return moveCounters[0];
    }
    if (positionToken == PositionToken.PLAYER_TWO) {
      return moveCounters[1];
    } else {
      return -1;
    }
  }

  @Override
  public GameStatus getCurrentGameStatus() {
    return this.gameStatus;
  }

  @Override
  public void setCurrentGameStatus(GameStatus gameStatus) {
    this.gameStatus = gameStatus;
  }

  @Override
  public void printBoard() {
    StringBuilder string = new StringBuilder();
    string.append(this.getPositionTokenAsString(0)).append(" - - - - - ")
        .append(this.getPositionTokenAsString(1)).append(" - - - - - ")
        .append(this.getPositionTokenAsString(2)).append(System.lineSeparator());
    string.append("|           |           |").append(System.lineSeparator());
    string.append("|     ").append(this.getPositionTokenAsString(3)).append(" - - ")
        .append(this.getPositionTokenAsString(4)).append(" - - ")
        .append(this.getPositionTokenAsString(5)).append("     |").append(System.lineSeparator());
    string.append("|     |     |     |     |").append(System.lineSeparator());
    string.append("|     | ").append(this.getPositionTokenAsString(6)).append(" - ")
        .append(this.getPositionTokenAsString(7)).append(" - ")
        .append(this.getPositionTokenAsString(8)).append(" |     |").append(System.lineSeparator());
    string.append("|     | |       | |     |").append(System.lineSeparator());
    string.append(this.getPositionTokenAsString(9)).append(" - - ")
        .append(this.getPositionTokenAsString(10)).append("-")
        .append(this.getPositionTokenAsString(11)).append("       ")
        .append(this.getPositionTokenAsString(12)).append("-")
        .append(this.getPositionTokenAsString(13)).append(" - - ")
        .append(this.getPositionTokenAsString(14)).append(System.lineSeparator());
    string.append("|     | |       | |     |").append(System.lineSeparator());
    string.append("|     | ").append(this.getPositionTokenAsString(15)).append(" - ")
        .append(this.getPositionTokenAsString(16)).append(" - ")
        .append(this.getPositionTokenAsString(17)).append(" |     |")
        .append(System.lineSeparator());
    string.append("|     |     |     |     |").append(System.lineSeparator());
    string.append("|     ").append(this.getPositionTokenAsString(18)).append(" - - ")
        .append(this.getPositionTokenAsString(19)).append(" - - ")
        .append(this.getPositionTokenAsString(20)).append("     |").append(System.lineSeparator());
    string.append("|           |           |").append(System.lineSeparator());
    string.append(this.getPositionTokenAsString(21)).append(" - - - - - ")
        .append(this.getPositionTokenAsString(22)).append(" - - - - - ")
        .append(this.getPositionTokenAsString(23)).append(System.lineSeparator());
    System.out.println(string);
  }

  public String getPositionTokenAsString(int position) {
    if (this.positionImpl[position].getPositionToken() == PositionToken.PLAYER_ONE) {
      return "1";
    } else if (this.positionImpl[position].getPositionToken() == PositionToken.PLAYER_TWO) {
      return "2";
    } else {
      return "0";
    }
  }

  @Override
  public Iterator<Position> iteratePositions() {
    return new Iterator() {
      private int index = 0;

      public boolean hasNext() {
        return this.index < positionImpl.length;
      }

      public PositionImpl next() {
        PositionImpl pos = positionImpl[this.index];
        ++this.index;
        return pos;
      }
    };
  }

  @Override
  public void undoMove(Move move) throws GameException {
    int toId = move.getToId();
    PositionToken playerToken = null;
    playerToken = this.getPosition(move.getToId()).getPositionToken();
    PositionToken enemy = (playerToken.equals(PositionToken.PLAYER_ONE)) ? PositionToken.PLAYER_TWO
        : PositionToken.PLAYER_ONE;
    if (move.getFromId() > -1) {
      if (move.getRemoveId() > -1) {
        this.getPosition(toId).setPositionToken(enemy);
      } else {
        this.getPosition(toId).setPositionToken(PositionToken.IS_EMPTY);
      }
    } else {
      if (move.getRemoveId() > -1) {
        this.getPosition(move.getRemoveId()).setPositionToken(enemy);
      } else {
        this.getPosition(toId).setPositionToken(PositionToken.IS_EMPTY);
      }
    }


  }

  private void movePlusPlus(PositionToken playerToken) {
    if (playerToken == PositionToken.PLAYER_ONE) {
      moveCounters[0]++;
    }
    if (playerToken == PositionToken.PLAYER_TWO) {
      moveCounters[1]++;
    }
  }

  private void tokenPlusPlus(PositionToken playerToken) {
    if (playerToken == PositionToken.PLAYER_ONE) {
      tokenCounters[0]++;
    }
    if (playerToken == PositionToken.PLAYER_TWO) {
      tokenCounters[1]++;
    }
  }

  private void tokenMinusMinus(PositionToken playerToken) {
    if (playerToken == PositionToken.PLAYER_ONE) {
      tokenCounters[0]--;
    }
    if (playerToken == PositionToken.PLAYER_TWO) {
      tokenCounters[1]--;
    }
  }

  private void initPositions() {
    this.positionImpl = new PositionImpl[24];

    for (int i = 0; i < 24; ++i) {
      this.positionImpl[i] = new PositionImpl(i);
      this.positionImpl[i].setPositionToken(PositionToken.IS_EMPTY);
    }

    this.positionImpl[0]
        .setNeighbors(new PositionImpl[]{this.positionImpl[1], this.positionImpl[9]});
    this.positionImpl[1].setNeighbors(
        new PositionImpl[]{this.positionImpl[0], this.positionImpl[2], this.positionImpl[4]});
    this.positionImpl[2]
        .setNeighbors(new PositionImpl[]{this.positionImpl[1], this.positionImpl[14]});
    this.positionImpl[3]
        .setNeighbors(new PositionImpl[]{this.positionImpl[4], this.positionImpl[10]});
    this.positionImpl[4].setNeighbors(
        new PositionImpl[]{this.positionImpl[1], this.positionImpl[3], this.positionImpl[5],
            this.positionImpl[7]});
    this.positionImpl[5]
        .setNeighbors(new PositionImpl[]{this.positionImpl[4], this.positionImpl[13]});
    this.positionImpl[6]
        .setNeighbors(new PositionImpl[]{this.positionImpl[7], this.positionImpl[11]});
    this.positionImpl[7].setNeighbors(
        new PositionImpl[]{this.positionImpl[4], this.positionImpl[6], this.positionImpl[8]});
    this.positionImpl[8]
        .setNeighbors(new PositionImpl[]{this.positionImpl[7], this.positionImpl[12]});
    this.positionImpl[9].setNeighbors(
        new PositionImpl[]{this.positionImpl[0], this.positionImpl[10], this.positionImpl[21]});
    this.positionImpl[10].setNeighbors(
        new PositionImpl[]{this.positionImpl[3], this.positionImpl[9], this.positionImpl[11],
            this.positionImpl[18]});
    this.positionImpl[11].setNeighbors(
        new PositionImpl[]{this.positionImpl[6], this.positionImpl[10], this.positionImpl[15]});
    this.positionImpl[12].setNeighbors(
        new PositionImpl[]{this.positionImpl[8], this.positionImpl[13], this.positionImpl[17]});
    this.positionImpl[13].setNeighbors(
        new PositionImpl[]{this.positionImpl[5], this.positionImpl[12], this.positionImpl[14],
            this.positionImpl[20]});
    this.positionImpl[14].setNeighbors(
        new PositionImpl[]{this.positionImpl[2], this.positionImpl[13], this.positionImpl[23]});
    this.positionImpl[15]
        .setNeighbors(new PositionImpl[]{this.positionImpl[11], this.positionImpl[16]});
    this.positionImpl[16].setNeighbors(
        new PositionImpl[]{this.positionImpl[15], this.positionImpl[17], this.positionImpl[19]});
    this.positionImpl[17]
        .setNeighbors(new PositionImpl[]{this.positionImpl[12], this.positionImpl[16]});
    this.positionImpl[18]
        .setNeighbors(new PositionImpl[]{this.positionImpl[10], this.positionImpl[19]});
    this.positionImpl[19].setNeighbors(
        new PositionImpl[]{this.positionImpl[16], this.positionImpl[18], this.positionImpl[20],
            this.positionImpl[22]});
    this.positionImpl[20]
        .setNeighbors(new PositionImpl[]{this.positionImpl[13], this.positionImpl[19]});
    this.positionImpl[21]
        .setNeighbors(new PositionImpl[]{this.positionImpl[9], this.positionImpl[22]});
    this.positionImpl[22].setNeighbors(
        new PositionImpl[]{this.positionImpl[19], this.positionImpl[21], this.positionImpl[23]});
    this.positionImpl[23]
        .setNeighbors(new PositionImpl[]{this.positionImpl[14], this.positionImpl[22]});
  }
}
