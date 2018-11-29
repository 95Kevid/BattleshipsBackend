package uk.gov.ukho.battleshipsboot.main.service;

import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.Position;
import uk.gov.ukho.battleshipsboot.model.ships.Column;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;

import java.util.Optional;

public class GameArenaService {

    public boolean addShip(Ship ship, GameArena gameArena){
        if(shipAlreadyExists(ship, gameArena)) {
            return false;
        }

        if(isShipOffBoard(ship)) {
            return false;
        }

        setOccupiedPositionsOfShip(ship);

        if(positionsAlreadyOccupied(ship, gameArena)) {
            return false;
        }

        gameArena.addShip(ship);
        return true;
    }


    private boolean shipAlreadyExists(Ship ship, GameArena gameArena) {
        return (gameArena.isShipOnBoard(ship));
    }

    public Ship getShipAtPosition(Position position, GameArena gameArena) {
        for(Ship aShip: gameArena.getShipsOnBoard()) {
            if(aShip.getOccupiedPositions().contains(position)) {
                return aShip;
            }
        }
        return null;
    }

    public boolean isShipOffBoard(Ship ship) {
        if(ship.getOrient() == Orientation.VERTICAL && ship.getOccupiedPosition(0).getRow() > 10) {
            return true;
        }
        if(ship.getOccupiedPosition(0).getCol().toString().charAt(0) + ship.getLength() > 'K') {
            return true;
        }
        return false;
    }

    public boolean positionsAlreadyOccupied(Ship ship, GameArena gameArena) {
        for(Ship aShip : gameArena.getShipsOnBoard()){
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

    public void registerHit(Position position, GameArena gameArena) {
        position.setHit();
        for(Ship ship : gameArena.getShipsOnBoard()) {
            if(ship.getOccupiedPositions().contains(position)) {
                setHitPositionOnShip(position, ship);
                if(ship.isSunk() == true) {
                    gameArena.addSunkenShip(ship);
                }
            }
            gameArena.addShotPosition(position);
        }
    }

    public Position getOccupiedPositionsOfShip(Position position, Ship ship) {
        Optional<Position> occupiedPosition = ship.getOccupiedPositions().stream()
                .filter(p -> p.equals(position)).findFirst();

        if(occupiedPosition.isPresent()) {
            return occupiedPosition.get();
        }
        return null;
    }

    public void setOccupiedPositionsOfShip(Ship ship) {
        if (ship.getOrient() == Orientation.VERTICAL) {
            for (int i = 1; i < ship.getLength(); i++) {
                Position pos = new Position(ship.getOccupiedPosition(0).getCol(),
                        ship.getOccupiedPosition(0).getRow() + i);
                ship.addOccupiedPosition(i, pos);
            }
        } else {
            for (int i = 1; i < ship.getLength(); i++) {
                char inputCol = ship.getOccupiedPosition(0).getCol().toString().charAt(0);
                int input = inputCol + i;
                String col = "" + (char) input;
                Position pos = new Position(Column.valueOf(col), ship.getOccupiedPosition(0).getRow());
                ship.addOccupiedPosition(i, pos);
            }
        }
    }

    public void setHitPositionOnShip(Position position, Ship ship) {
        for(Position aPosition: ship.getOccupiedPositions()) {
            if(aPosition.equals(position)) {
                aPosition.setHit();
            }
        }

        boolean allPositionsHit = ship.getOccupiedPositions()
                .stream()
                .allMatch(p -> p.isHit());

        if(allPositionsHit) {
            ship.sink();
        }
    }
}
