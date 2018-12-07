package uk.gov.ukho.battleshipsboot.service;

import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;
import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.ships.Column;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;

import java.util.List;
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

    public Ship getShipAtPosition(BoardPosition boardPosition, GameArena gameArena) {
        for(Ship aShip: gameArena.getShipsOnBoard()) {
            if(aShip.getOccupiedBoardPositions().contains(boardPosition)) {
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
            boolean isShipOccupyingPosition = aShip.getOccupiedBoardPositions()
                    .stream()
                    .anyMatch(ship.getOccupiedBoardPositions()::contains);

            if(isShipOccupyingPosition)
            {
                return true;
            }
        }
        return false;
    }

    public void registerHit(BoardPosition boardPosition, GameArena gameArena) {
        boardPosition.setHit();
        for(Ship ship : gameArena.getShipsOnBoard()) {
            if(ship.getOccupiedBoardPositions().contains(boardPosition)) {
                setHitPositionOnShip(boardPosition, ship);
                if(ship.isSunk() == true) {
                    gameArena.addSunkenShip(ship);
                }
            }
            gameArena.addShotPosition(boardPosition);
        }
    }

    public BoardPosition getOccupiedPositionsOfShip(BoardPosition boardPosition, Ship ship) {
        Optional<BoardPosition> occupiedPosition = ship.getOccupiedBoardPositions().stream()
                .filter(p -> p.equals(boardPosition)).findFirst();

        if(occupiedPosition.isPresent()) {
            return occupiedPosition.get();
        }
        return null;
    }

    public void setOccupiedPositionsOfShip(Ship ship) {
        if (ship.getOrient() == Orientation.VERTICAL) {
            for (int i = 1; i < ship.getLength(); i++) {
                BoardPosition pos = new BoardPosition(ship.getOccupiedPosition(0).getCol(),
                        ship.getOccupiedPosition(0).getRow() + i);
                List<BoardPosition> occupiedBoardPositions = ship.getOccupiedBoardPositions();
                occupiedBoardPositions.add(i, pos);
                ship.setOccupiedPosition(occupiedBoardPositions);
            }
        } else {
            for (int i = 1; i < ship.getLength(); i++) {
                char inputCol = ship.getOccupiedPosition(0).getCol().toString().charAt(0);
                int input = inputCol + i;
                String col = "" + (char) input;
                BoardPosition pos = new BoardPosition(Column.valueOf(col), ship.getOccupiedPosition(0).getRow());
                List<BoardPosition> occupiedBoardPositions = ship.getOccupiedBoardPositions();
                occupiedBoardPositions.add(i, pos);
                ship.setOccupiedPosition(occupiedBoardPositions);
            }
        }
    }

    public void setHitPositionOnShip(BoardPosition boardPosition, Ship ship) {
        for(BoardPosition aBoardPosition : ship.getOccupiedBoardPositions()) {
            if(aBoardPosition.equals(boardPosition)) {
                aBoardPosition.setHit();
            }
        }

        boolean allPositionsHit = ship.getOccupiedBoardPositions()
                .stream()
                .allMatch(p -> p.isHit());

        if(allPositionsHit) {
            ship.setSunk(true);
        }
    }
}
