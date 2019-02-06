package com.harragan.battleshipsboot.model.game;
import com.harragan.battleshipsboot.model.ships.Ship;

import javax.persistence.*;

import java.util.*;

import static javax.persistence.CascadeType.PERSIST;

@Entity
public class GameArena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = PERSIST)
    private Set<Ship> shipsOnBoard = new HashSet<>();

    private boolean allShipsPlaced;

    @OneToMany(cascade = PERSIST)
    private List<BoardPosition> shotBoardPositions = new ArrayList<>();

    @OneToMany(cascade = PERSIST)
    private Set<Ship> sunkShips = new HashSet<>();

    private int gameArenaSize;

    public GameArena(){}

    public GameArena(int gameArenaSize){
        this.gameArenaSize = gameArenaSize;
    }

    public List<Ship> getSunkShips() {
        return new ArrayList<>(sunkShips);
    }

    public Set<Ship> getShipsOnBoard() {
        return shipsOnBoard;
    }

    public void addShip(Ship ship) {
        shipsOnBoard.add(ship);
    }

    public boolean isShipOnBoard(Ship ship) {
        return getShipsOnBoard().contains(ship);
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

    public void setShipsOnBoard(Set<Ship> shipsOnBoard) {
        this.shipsOnBoard = shipsOnBoard;
    }

    public void setShotBoardPositions(List<BoardPosition> shotBoardPositions) {
        this.shotBoardPositions = shotBoardPositions;
    }

    public int getGameArenaSize() {
        return gameArenaSize;
    }

    public void setSunkShips(Set<Ship> sunkShips) {
        this.sunkShips = sunkShips;
    }

    public boolean isAllShipsPlaced() {
        return allShipsPlaced;
    }

    public void setAllShipsPlaced(boolean input) {
        allShipsPlaced = input;
    }
}
