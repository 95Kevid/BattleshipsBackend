package com.harragan.battleshipsboot.model.game;

import static javax.persistence.CascadeType.ALL;

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GameArena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = ALL)
    private Set<Ship> shipsOnBoard = new HashSet<>();

    private boolean allShipsPlaced;

    @OneToMany(cascade = ALL)
    private Set<BoardPosition> shotBoardPositions = new HashSet<>();

    @OneToMany(cascade = ALL)
    private Set<Ship> sunkShips = new HashSet<>();

    private int gameArenaSize;

    public GameArena() {
    }

    public GameArena(final int gameArenaSize) {
        this.gameArenaSize = gameArenaSize;
    }

    public List<Ship> getSunkShips() {
        return new ArrayList<>(sunkShips);
    }

    public void setSunkShips(final Set<Ship> sunkShips) {
        this.sunkShips = sunkShips;
    }

    public Set<Ship> getShipsOnBoard() {
        return shipsOnBoard;
    }

    public void setShipsOnBoard(final Set<Ship> shipsOnBoard) {
        this.shipsOnBoard = shipsOnBoard;
    }

    public void addShip(final Ship ship) {
        shipsOnBoard.add(ship);
    }

    public boolean isShipOnBoard(final Ship ship) {
        return getShipsOnBoard().contains(ship);
    }

    public void addShotPosition(final BoardPosition boardPosition) {
        this.shotBoardPositions.add(boardPosition);
    }

    public void clearArena() {
        shipsOnBoard.clear();
        shotBoardPositions.clear();
        sunkShips.clear();
    }

    public void addSunkenShip(final Ship ship) {
        sunkShips.add(ship);
    }

    public int getGameArenaSize() {
        return gameArenaSize;
    }

    public boolean isAllShipsPlaced() {
        return allShipsPlaced;
    }

    public void setAllShipsPlaced(final boolean input) {
        allShipsPlaced = input;
    }

    public Set<BoardPosition> getShotPositions() {
        return this.shotBoardPositions;
    }
}
