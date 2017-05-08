package de.fhws.gos.ss17.game;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.GameStatus;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.game.Position;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Neuer on 03.05.2017.
 */
public class Board implements de.fhws.gos.core.logic.Board{
    private Position[] positions;
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
            this.tokenMinusMinus(this.positions[move.getFromId()].getPositionToken());
            this.positions[move.getFromId()].setPositionToken(PositionToken.IS_EMPTY);
        }

        if(move.getToId() != -1) {
            System.out.println(positionToken + " TO " + move.getToId());
            this.tokenPlusPlus(positionToken);
            this.positions[move.getToId()].setPositionToken(positionToken);
        }

        if(move.getRemoveId() != -1) {
            System.out.println(positionToken + " REM: " + move.getRemoveId());
            this.tokenMinusMinus(this.positions[move.getRemoveId()].getPositionToken());
            this.positions[move.getRemoveId()].setPositionToken(PositionToken.IS_EMPTY);
        }

    }

    @Override
    public Move getLastMove() {
        return this.moveHistory.getLast();
    }

    @Override
    public Position getPosition(int i) throws GameException {
        if(i >= 0 && i < 24) {
            return this.positions[i];
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
        if(this.positions[position].getPositionToken() == PositionToken.PLAYER_ONE) return "1";
        else if(this.positions[position].getPositionToken() == PositionToken.PLAYER_TWO) return "2";
        else return "0";
    }

    @Override
    public Iterator<de.fhws.gos.core.logic.Position> iteratePositions() {
        return new Iterator() {
            private int index = 0;

            public boolean hasNext() {
                return this.index < positions.length;
            }

            public Position next() {
                Position pos = positions[this.index];
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
        this.positions = new Position[24];

        for(int i = 0; i < 24; ++i) {
            this.positions[i] = new Position(i);
            this.positions[i].setPositionToken(PositionToken.IS_EMPTY);
        }

        this.positions[0].setNeighbors(new Position[]{this.positions[1], this.positions[9]});
        this.positions[1].setNeighbors(new Position[]{this.positions[0], this.positions[2], this.positions[4]});
        this.positions[2].setNeighbors(new Position[]{this.positions[1], this.positions[14]});
        this.positions[3].setNeighbors(new Position[]{this.positions[4], this.positions[10]});
        this.positions[4].setNeighbors(new Position[]{this.positions[1], this.positions[3], this.positions[5], this.positions[7]});
        this.positions[5].setNeighbors(new Position[]{this.positions[4], this.positions[13]});
        this.positions[6].setNeighbors(new Position[]{this.positions[7], this.positions[11]});
        this.positions[7].setNeighbors(new Position[]{this.positions[4], this.positions[6], this.positions[8]});
        this.positions[8].setNeighbors(new Position[]{this.positions[7], this.positions[12]});
        this.positions[9].setNeighbors(new Position[]{this.positions[0], this.positions[10], this.positions[21]});
        this.positions[10].setNeighbors(new Position[]{this.positions[3], this.positions[9], this.positions[11], this.positions[18]});
        this.positions[11].setNeighbors(new Position[]{this.positions[6], this.positions[10], this.positions[15]});
        this.positions[12].setNeighbors(new Position[]{this.positions[8], this.positions[13], this.positions[17]});
        this.positions[13].setNeighbors(new Position[]{this.positions[5], this.positions[12], this.positions[14], this.positions[20]});
        this.positions[14].setNeighbors(new Position[]{this.positions[2], this.positions[13], this.positions[23]});
        this.positions[15].setNeighbors(new Position[]{this.positions[11], this.positions[16]});
        this.positions[16].setNeighbors(new Position[]{this.positions[15], this.positions[17], this.positions[19]});
        this.positions[17].setNeighbors(new Position[]{this.positions[12], this.positions[16]});
        this.positions[18].setNeighbors(new Position[]{this.positions[10], this.positions[19]});
        this.positions[19].setNeighbors(new Position[]{this.positions[16], this.positions[18], this.positions[20], this.positions[22]});
        this.positions[20].setNeighbors(new Position[]{this.positions[13], this.positions[19]});
        this.positions[21].setNeighbors(new Position[]{this.positions[9], this.positions[22]});
        this.positions[22].setNeighbors(new Position[]{this.positions[19], this.positions[21], this.positions[23]});
        this.positions[23].setNeighbors(new Position[]{this.positions[14], this.positions[22]});
    }
}
