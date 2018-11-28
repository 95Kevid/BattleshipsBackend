package uk.gov.ukho.battleshipsboot.ships;

import uk.gov.ukho.battleshipsboot.main.Column;
import uk.gov.ukho.battleshipsboot.main.Orientation;
import uk.gov.ukho.battleshipsboot.main.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Position getOccupiedPosition(Position position) {
        Optional<Position> occupiedPosition = occupiedPositions.stream()
                .filter(p -> p.equals(position)).findFirst();

        if(occupiedPosition.isPresent()) {
            return occupiedPosition.get();
        }
        return null;
    }

    public List<Position> getOccupiedPositions() {
        return new ArrayList<>(occupiedPositions);
    }

    public void setOccupiedPositions() {
        if(orient == Orientation.VERTICAL) {
            for(int i = 1; i < LENGTH; i++) {
                Position pos = new Position(position.getCol(), position.getRow() + i);
                occupiedPositions.add(i, pos);
            }
        }
        else {
            for(int i = 1; i < LENGTH; i++) {
                char inputCol =  position.getCol().toString().charAt(0);
                int input = inputCol + i;
                String col = "" + (char) input;
                Position pos = new Position(Column.valueOf(col), position.getRow());
                occupiedPositions.add(1, pos);
            }
        }
    }

    public void setHitPosition(Position position) {
        for(Position aPosition: occupiedPositions) {
            if(aPosition.equals(position)) {
                aPosition.setHit();
            }
        }

        boolean allPositionsHit = occupiedPositions.stream().allMatch(p -> p.isHit());
        if(allPositionsHit) {
            isSunk = true;
        }
    }

    public boolean isSunk() {
        return isSunk;
    }

}
