package uk.gov.ukho.battleshipsboot.main.service;

import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.Position;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;

public class GameArenaService {

    public boolean addShip(Ship ship, GameArena gameArena){
        if(shipAlreadyExists(ship, gameArena)) {
            return false;
        }

        if(isShipOffBoard(ship)) {
            return false;
        }

        ship.setOccupiedPositions();

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
                ship.setHitPosition(position);
                if(ship.isSunk() == true) {
                    gameArena.getSunkShips().add(ship);
                }
            }
            gameArena.addShotPosition(position);
        }
    }
}
