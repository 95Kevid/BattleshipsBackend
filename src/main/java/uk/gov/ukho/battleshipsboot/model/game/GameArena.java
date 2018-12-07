package uk.gov.ukho.battleshipsboot.model.game;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.*;

import static javax.persistence.CascadeType.PERSIST;

@Entity
public class GameArena {

    @Id
    private int id;
    @OneToMany(cascade = PERSIST)
    private List<Ship> shipsOnBoard = new ArrayList<>();
    @OneToMany(cascade = PERSIST)
    private List<BoardPosition> shotBoardPositions = new ArrayList<>();
    @OneToMany(cascade = PERSIST)
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

    public void addShotPosition(BoardPosition boardPosition) {
        this.shotBoardPositions.add(boardPosition);
    }

    public void clearArena() {
        shipsOnBoard.clear();
        shotBoardPositions.clear();
        sunkShips.clear();
    }

    public void addSunkenShip(Ship ship) {
        sunkShips.add(ship);
    }

    public void setShipsOnBoard(List<Ship> shipsOnBoard) {
        this.shipsOnBoard = shipsOnBoard;
    }

    public void setShotBoardPositions(List<BoardPosition> shotBoardPositions) {
        this.shotBoardPositions = shotBoardPositions;
    }

    public void setSunkShips(List<Ship> sunkShips) {
        this.sunkShips = sunkShips;
    }
}
