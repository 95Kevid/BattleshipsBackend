package com.harragan.battleshipsboot.service;

import com.harragan.battleshipsboot.model.game.GameArena;

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;
import com.harragan.battleshipsboot.service.exceptions.IllegalBoardPlacementException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameArenaService {

    public GameArena createGameArena(final int gameArenaSize) {
        final GameArena gameArena = new GameArena(gameArenaSize);
        return gameArena;
    }

    public void addShip(final Ship ship, final GameArena gameArena) {
        setOccupiedPositionsOfShip(ship);
        checkShipCanBePlaced(ship, gameArena);
        addShipToGameArena(ship, gameArena);
    }

    private void addShipToGameArena(final Ship ship, final GameArena gameArena) {
        gameArena.addShip(ship);
        if (gameArena.getShipsOnBoard().size() == 5) {
            gameArena.setAllShipsPlaced(true);
        }
    }

    private void checkShipCanBePlaced(final Ship ship, final GameArena gameArena) {
        if (shipAlreadyExists(ship, gameArena)) {
            throw new IllegalBoardPlacementException(
                    "The "
                            + ship.getClass().getSimpleName().toLowerCase()
                            + " has already been placed on the board");
        }

        if (isShipOffBoard(ship, gameArena)) {
            throw new IllegalBoardPlacementException(
                    "Ship is positioned off board."
                            + " Please ensure that all positions are valid positions.");
        }
        if (positionsAlreadyOccupied(ship, gameArena)) {
            throw new IllegalBoardPlacementException(
                    "This board position would cause the ship"
                            + " to overlap with another ship already placed on the board.");
        }
    }

    private boolean shipAlreadyExists(final Ship ship, final GameArena gameArena) {
        return (gameArena.isShipOnBoard(ship));
    }

    public Ship getShipAtPosition(final BoardPosition boardPosition, final GameArena gameArena) {
        for (final Ship ship : gameArena.getShipsOnBoard()) {
            if (ship.getOccupiedBoardPositions().contains(boardPosition)) {
                return ship;
            }
        }
        return null;
    }

    public boolean isShipOffBoard(final Ship ship, final GameArena gameArena) {
        if (ship.getOrientation() == Orientation.VERTICAL
                && ship.getOccupiedBoardPositions().get(0).getRow() + ship.getType().getLength()
                > gameArena.getGameArenaSize() + 1) {
            return true;
        }
        return ship.getOrientation() == Orientation.HORIZONTAL
                && ship.getOccupiedBoardPositions().get(0).getCol() + ship.getType().getLength()
                > gameArena.getGameArenaSize() + 65;
    }

    private boolean positionsAlreadyOccupied(final Ship ship, final GameArena gameArena) {
        for (final Ship gameArenaShip : gameArena.getShipsOnBoard()) {
            final boolean isShipOccupyingPosition =
                    gameArenaShip.getOccupiedBoardPositions().stream()
                            .anyMatch(ship.getOccupiedBoardPositions()::contains);

            if (isShipOccupyingPosition) {
                return true;
            }
        }
        return false;
    }

    public void registerHit(final BoardPosition boardPosition, final GameArena gameArena) {
        boardPosition.setHit(true);
        for (final Ship ship : gameArena.getShipsOnBoard()) {
            if (ship.getOccupiedBoardPositions().contains(boardPosition)) {
                setHitPositionOnShip(boardPosition, ship);
                if (ship.isSunk()) {
                    gameArena.addSunkenShip(ship);
                }
            }
            gameArena.addShotPosition(boardPosition);
        }
    }

    public BoardPosition getOccupiedPositionsOfShip(final BoardPosition boardPosition, final Ship ship) {
        final Optional<BoardPosition> occupiedPosition =
                ship.getOccupiedBoardPositions().stream().filter(p -> p.equals(boardPosition)).findFirst();

        if (occupiedPosition.isPresent()) {
            return occupiedPosition.get();
        }
        return null;
    }

    private void setOccupiedPositionsOfShip(final Ship ship) {
        if (ship.getOrientation() == Orientation.VERTICAL) {
            setOccupiedPositionsOfShipWhenVertical(ship);
        } else {
            setOccupiedPositionsOfShipWhenHorizontal(ship);
        }
    }

    private void setOccupiedPositionsOfShipWhenHorizontal(final Ship ship) {
        for (int i = 1; i < ship.getType().getLength(); i++) {
            final char inputCol = ship.getOccupiedBoardPositions().get(0).getCol();
            final int input = inputCol + i;
            final BoardPosition pos =
                    BoardPositionFactory.createBoardPosition(
                            (char) input, ship.getOccupiedBoardPositions().get(0).getRow());
            final List<BoardPosition> occupiedBoardPositions = ship.getOccupiedBoardPositions();
            occupiedBoardPositions.add(i, pos);
            ship.setOccupiedBoardPositions(occupiedBoardPositions);
        }
    }

    private void setOccupiedPositionsOfShipWhenVertical(final Ship ship) {
        for (int i = 1; i < ship.getType().getLength(); i++) {
            final BoardPosition pos =
                    BoardPositionFactory.createBoardPosition(
                            ship.getOccupiedBoardPositions().get(0).getCol(), ship.getOccupiedBoardPositions().get(0).getRow() + i);
            final List<BoardPosition> occupiedBoardPositions = ship.getOccupiedBoardPositions();
            occupiedBoardPositions.add(i, pos);
            ship.setOccupiedBoardPositions(occupiedBoardPositions);
        }
    }

    private void setHitPositionOnShip(final BoardPosition boardPosition, final Ship ship) {
        for (final BoardPosition occupiedBoardPosition : ship.getOccupiedBoardPositions()) {
            if (occupiedBoardPosition.equals(boardPosition)) {
                occupiedBoardPosition.setHit(true);
                break;
            }
        }
        final boolean allPositionsHit = ship.getOccupiedBoardPositions().stream().allMatch(p -> p.isHit());
        if (allPositionsHit) {
            ship.setSunk(true);
        }
    }
}
