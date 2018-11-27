package uk.gov.ukho.battleshipsboot.main;
import uk.gov.ukho.battleshipsboot.ships.Ship;

import java.util.*;

public class GameArena {

    private List<Ship> shipsOnBoard= new ArrayList<>();

    public boolean addShip(Ship ship){
        if(shipAlreadyExists(ship)) {
            return false;
        }

        if(isShipOffBoard(ship)) {
            return false;
        }

        ship.setOccupiedPositions();

        if(positionsAlreadyOccupied(ship)) {
            return false;
        }

        shipsOnBoard.add(ship);
        return true;
    }

    private boolean shipAlreadyExists(Ship ship) {
        return (shipsOnBoard.contains(ship));
    }

    public Ship getShipAtPosition(Position position) {
        for(Ship aShip: shipsOnBoard) {
            if(aShip.getOccupiedPositions().contains(position)) {
                return aShip;
            }
        }
        return null;
    }

    public boolean isShipOffBoard(Ship ship) {
        if(ship.getOrient() == Orientation.VERTICAL && ship.getPosition(0).getRow() > 10) {
                return true;
        }
        if(ship.getPosition(0).getCol().toString().charAt(0) + ship.getLength() > 'K') {
                return true;
        }
        return false;
    }

    public void clear(){
        shipsOnBoard.clear();
    }

    public boolean positionsAlreadyOccupied(Ship ship) {
        for(Ship aShip : shipsOnBoard){
            boolean isShipOccupyingPosition = aShip.getOccupiedPositions()
                    .stream()
                    .anyMatch(ship.getOccupiedPositions()::contains);

            if(isShipOccupyingPosition)
            {
                return true;
            }
        }
        return false;
    }
}
