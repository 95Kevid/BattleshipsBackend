package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class Ship {
    private final int LENGTH;
    private Position position;
    private List<Position> occupiedPositions;
    private boolean isSunk;
    private Orientation orient;

    public Ship(int length, Orientation orient, Position position){
        LENGTH = length;
        occupiedPositions = new ArrayList<>();
        occupiedPositions.add(0, position);
        isSunk = false;
        this.orient = orient;
        this.position = position;
    }

    public int getLength() {
        return LENGTH;
    }

    public Orientation getOrient() {
        return orient;
    }

    public Position getOccupiedPosition(int anInt) {
        return occupiedPositions.get(anInt);
    }

    public List<Position> getOccupiedPositions() {
        return new ArrayList<>(occupiedPositions);
    }

    public void addOccupiedPosition(int index, Position position) {
        occupiedPositions.add(index, position);
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void sink() {
        isSunk = true;
    }

}
