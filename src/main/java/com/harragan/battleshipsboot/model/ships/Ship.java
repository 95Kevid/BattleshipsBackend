package com.harragan.battleshipsboot.model.ships;

import static javax.persistence.CascadeType.PERSIST;

import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.model.game.Orientation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public abstract class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int length;

    @OneToMany(cascade = PERSIST)
    private List<BoardPosition> occupiedBoardPositions;

    private boolean isSunk;
    private Orientation orient;

    public Ship() {
    }

    public Ship(final int length, final Orientation orient, final BoardPosition boardPosition) {
        this.length = length;
        occupiedBoardPositions = new ArrayList<>();
        occupiedBoardPositions.add(0, boardPosition);
        isSunk = false;
        this.orient = orient;
    }

    public int getLength() {
        return length;
    }

    public Orientation getOrient() {
        return orient;
    }

    public BoardPosition getOccupiedPosition(final int anInt) {
        return occupiedBoardPositions.get(anInt);
    }

    public List<BoardPosition> getOccupiedBoardPositions() {
        return occupiedBoardPositions;
    }

    public void setOccupiedPosition(final List<BoardPosition> occupiedBoardPosition) {
        this.occupiedBoardPositions = occupiedBoardPosition;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void setSunk(final Boolean isSunk) {
        this.isSunk = isSunk;
    }

    @Override
    public boolean equals(final Object ship) {
        final Ship inputShip = (Ship) ship;
        return this.getClass().equals(inputShip.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getClass());
    }
}
