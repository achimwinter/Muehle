package de.fhws.gos.ss17.game;

import de.fhws.gos.core.utils.PositionToken;

/**
 * Created by Neuer on 03.05.2017.
 */
public class Position implements de.fhws.gos.core.logic.Position {
    private int id;
    private Position[] neighbors;
    private PositionToken positionToken;

    public Position(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setNeighbors(de.fhws.gos.core.logic.Position[] positions) {
        this.neighbors = neighbors;
    }

    @Override
    public void setNeighbors(Position[] positions) {
        this.neighbors = neighbors;
    }

    @Override
    public Position[] getNeighbors() {
        return this.neighbors;
    }

    @Override
    public PositionToken getPositionToken() {
        return this.positionToken;
    }

    @Override
    public void setPositionToken(PositionToken positionToken) {
        this.positionToken = positionToken;
    }

    @Override
    public boolean isAvailable() {
        return this.positionToken == PositionToken.IS_EMPTY;
    }
}
