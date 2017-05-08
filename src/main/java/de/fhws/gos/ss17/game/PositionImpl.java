package de.fhws.gos.ss17.game;

import de.fhws.gos.core.logic.Position;
import de.fhws.gos.core.utils.PositionToken;

/**
 * Created by Neuer on 03.05.2017.
 */
public class PositionImpl implements Position {
    private int id;
    private PositionImpl[] neighbors;
    private PositionToken positionToken;

    public PositionImpl(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setNeighbors(PositionImpl[] positions) {
        this.neighbors = positions;
    }

    @Override
    public PositionImpl[] getNeighbors() {
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
