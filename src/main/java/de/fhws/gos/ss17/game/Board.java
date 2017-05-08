package de.fhws.gos.ss17.game;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.GameStatus;
import de.fhws.gos.core.utils.PositionToken;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by Neuer on 03.05.2017.
 */
public class Board implements de.fhws.gos.core.logic.Board{
    private PositionImpl[] positionImpls;
    private GameStatus gameStatus;
    private int[] tokenCounters;
    private int[] moveCounters;
    private LinkedList<Move> moveHistory;

    public Board() {
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

    @Override
    public void executeMove(Move move, PositionToken positionToken) {
        this.movePlusPlus(positionToken);
        this.moveHistory.add(move);
        if(move.getFromId() != -1) {
            System.out.println(positionToken + " FR " + move.getFromId());
            this.tokenMinusMinus(this.positionImpls[move.getFromId()].getPositionToken());
            this.positionImpls[move.getFromId()].setPositionToken(PositionToken.IS_EMPTY);
        }

        if(move.getToId() != -1) {
            System.out.println(positionToken + " TO " + move.getToId());
            this.tokenPlusPlus(positionToken);
            this.positionImpls[move.getToId()].setPositionToken(positionToken);
        }

        if(move.getRemoveId() != -1) {
            System.out.println(positionToken + " REM: " + move.getRemoveId());
            this.tokenMinusMinus(this.positionImpls[move.getRemoveId()].getPositionToken());
            this.positionImpls[move.getRemoveId()].setPositionToken(PositionToken.IS_EMPTY);
        }

    }

    @Override
    public Move getLastMove() {
        try {
            return (Move) this.moveHistory.getLast();
        }catch (NoSuchElementException ex){
            return null;
        }

    }

    @Override
    public PositionImpl getPosition(int i) throws GameException {
        if(i >= 0 && i < 24) {
            return this.positionImpls[i];
        } else {
            throw new GameException("Blödl... " + i + "geht net!");
        }
    }

    @Override
    public int getNumberOfTokensForPlayer(PositionToken positionToken) {
        if(positionToken == PositionToken.PLAYER_ONE) return tokenCounters[0];
        if(positionToken == PositionToken.PLAYER_TWO) return tokenCounters[1];
        else return -1;
    }

    @Override
    public int getNumberOfMovesForPlayer(PositionToken positionToken) {
        if(positionToken == PositionToken.PLAYER_ONE) return moveCounters[0];
        if(positionToken == PositionToken.PLAYER_TWO) return moveCounters[1];
        else return -1;
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
        string.append(this.getPositionTokenAsString(0)).append(" - - - - - ").append(this.getPositionTokenAsString(1)).append(" - - - - - ").append(this.getPositionTokenAsString(2)).append(System.lineSeparator());
        string.append("|           |           |").append(System.lineSeparator());
        string.append("|     ").append(this.getPositionTokenAsString(3)).append(" - - ").append(this.getPositionTokenAsString(4)).append(" - - ").append(this.getPositionTokenAsString(5)).append("     |").append(System.lineSeparator());
        string.append("|     |     |     |     |").append(System.lineSeparator());
        string.append("|     | ").append(this.getPositionTokenAsString(6)).append(" - ").append(this.getPositionTokenAsString(7)).append(" - ").append(this.getPositionTokenAsString(8)).append(" |     |").append(System.lineSeparator());
        string.append("|     | |       | |     |").append(System.lineSeparator());
        string.append(this.getPositionTokenAsString(9)).append(" - - ").append(this.getPositionTokenAsString(10)).append("-").append(this.getPositionTokenAsString(11)).append("       ").append(this.getPositionTokenAsString(12)).append("-").append(this.getPositionTokenAsString(13)).append(" - - ").append(this.getPositionTokenAsString(14)).append(System.lineSeparator());
        string.append("|     | |       | |     |").append(System.lineSeparator());
        string.append("|     | ").append(this.getPositionTokenAsString(15)).append(" - ").append(this.getPositionTokenAsString(16)).append(" - ").append(this.getPositionTokenAsString(17)).append(" |     |").append(System.lineSeparator());
        string.append("|     |     |     |     |").append(System.lineSeparator());
        string.append("|     ").append(this.getPositionTokenAsString(18)).append(" - - ").append(this.getPositionTokenAsString(19)).append(" - - ").append(this.getPositionTokenAsString(20)).append("     |").append(System.lineSeparator());
        string.append("|           |           |").append(System.lineSeparator());
        string.append(this.getPositionTokenAsString(21)).append(" - - - - - ").append(this.getPositionTokenAsString(22)).append(" - - - - - ").append(this.getPositionTokenAsString(23)).append(System.lineSeparator());
        System.out.println(string);
    }

    public String getPositionTokenAsString(int position) {
        if(this.positionImpls[position].getPositionToken() == PositionToken.PLAYER_ONE) return "1";
        else if(this.positionImpls[position].getPositionToken() == PositionToken.PLAYER_TWO) return "2";
        else return "0";
    }

    @Override
    public Iterator<de.fhws.gos.core.logic.Position> iteratePositions() {
        return new Iterator() {
            private int index = 0;

            public boolean hasNext() {
                return this.index < positionImpls.length;
            }

            public PositionImpl next() {
                PositionImpl pos = positionImpls[this.index];
                ++this.index;
                return pos;
            }
        };
    }

    private void movePlusPlus(PositionToken playerToken){
        if(playerToken == PositionToken.PLAYER_ONE)moveCounters[0]++;
        if(playerToken == PositionToken.PLAYER_TWO)moveCounters[1]++;
    }

    private void tokenPlusPlus(PositionToken playerToken){
        if(playerToken == PositionToken.PLAYER_ONE)tokenCounters[0]++;
        if(playerToken == PositionToken.PLAYER_TWO)tokenCounters[1]++;
    }

    private void tokenMinusMinus(PositionToken playerToken){
        if(playerToken == PositionToken.PLAYER_ONE)tokenCounters[0]--;
        if(playerToken == PositionToken.PLAYER_TWO)tokenCounters[1]--;
    }

    private void initPositions() {
        this.positionImpls = new PositionImpl[24];

        for(int i = 0; i < 24; ++i) {
            this.positionImpls[i] = new PositionImpl(i);
            this.positionImpls[i].setPositionToken(PositionToken.IS_EMPTY);
        }

        this.positionImpls[0].setNeighbors(new PositionImpl[]{this.positionImpls[1], this.positionImpls[9]});
        this.positionImpls[1].setNeighbors(new PositionImpl[]{this.positionImpls[0], this.positionImpls[2], this.positionImpls[4]});
        this.positionImpls[2].setNeighbors(new PositionImpl[]{this.positionImpls[1], this.positionImpls[14]});
        this.positionImpls[3].setNeighbors(new PositionImpl[]{this.positionImpls[4], this.positionImpls[10]});
        this.positionImpls[4].setNeighbors(new PositionImpl[]{this.positionImpls[1], this.positionImpls[3], this.positionImpls[5], this.positionImpls[7]});
        this.positionImpls[5].setNeighbors(new PositionImpl[]{this.positionImpls[4], this.positionImpls[13]});
        this.positionImpls[6].setNeighbors(new PositionImpl[]{this.positionImpls[7], this.positionImpls[11]});
        this.positionImpls[7].setNeighbors(new PositionImpl[]{this.positionImpls[4], this.positionImpls[6], this.positionImpls[8]});
        this.positionImpls[8].setNeighbors(new PositionImpl[]{this.positionImpls[7], this.positionImpls[12]});
        this.positionImpls[9].setNeighbors(new PositionImpl[]{this.positionImpls[0], this.positionImpls[10], this.positionImpls[21]});
        this.positionImpls[10].setNeighbors(new PositionImpl[]{this.positionImpls[3], this.positionImpls[9], this.positionImpls[11], this.positionImpls[18]});
        this.positionImpls[11].setNeighbors(new PositionImpl[]{this.positionImpls[6], this.positionImpls[10], this.positionImpls[15]});
        this.positionImpls[12].setNeighbors(new PositionImpl[]{this.positionImpls[8], this.positionImpls[13], this.positionImpls[17]});
        this.positionImpls[13].setNeighbors(new PositionImpl[]{this.positionImpls[5], this.positionImpls[12], this.positionImpls[14], this.positionImpls[20]});
        this.positionImpls[14].setNeighbors(new PositionImpl[]{this.positionImpls[2], this.positionImpls[13], this.positionImpls[23]});
        this.positionImpls[15].setNeighbors(new PositionImpl[]{this.positionImpls[11], this.positionImpls[16]});
        this.positionImpls[16].setNeighbors(new PositionImpl[]{this.positionImpls[15], this.positionImpls[17], this.positionImpls[19]});
        this.positionImpls[17].setNeighbors(new PositionImpl[]{this.positionImpls[12], this.positionImpls[16]});
        this.positionImpls[18].setNeighbors(new PositionImpl[]{this.positionImpls[10], this.positionImpls[19]});
        this.positionImpls[19].setNeighbors(new PositionImpl[]{this.positionImpls[16], this.positionImpls[18], this.positionImpls[20], this.positionImpls[22]});
        this.positionImpls[20].setNeighbors(new PositionImpl[]{this.positionImpls[13], this.positionImpls[19]});
        this.positionImpls[21].setNeighbors(new PositionImpl[]{this.positionImpls[9], this.positionImpls[22]});
        this.positionImpls[22].setNeighbors(new PositionImpl[]{this.positionImpls[19], this.positionImpls[21], this.positionImpls[23]});
        this.positionImpls[23].setNeighbors(new PositionImpl[]{this.positionImpls[14], this.positionImpls[22]});
    }
}
