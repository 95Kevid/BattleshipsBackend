package uk.gov.ukho.battleshipsboot.model.game;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;

import java.util.*;

public class GameArena {

    private List<Ship> shipsOnBoard = new ArrayList<>();
    private List<Position> shotPositions = new ArrayList<>();
    private List<Ship> sunkShips = new ArrayList<>();

    public List<Ship> getSunkShips() {
        return new ArrayList<>(sunkShips);
    }

    public List<Ship> getShipsOnBoard() {
        return shipsOnBoard;
    }

    public void addShip(Ship ship) {
        shipsOnBoard.add(ship);
    }

    public boolean isShipOnBoard(Ship ship) {
        return shipsOnBoard.contains(ship);
    }

    public void addShotPosition(Position position) {
        this.shotPositions.add(position);
    }

    public void clearArena() {
        shipsOnBoard.clear();
        shotPositions.clear();
        sunkShips.clear();
    }

    public void addSunkenShip(Ship ship) {
        sunkShips.add(ship);
    }
}
