package com.harragan.battleshipsboot.model.ships;

import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.model.game.Orientation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.PERSIST;

@Entity
public abstract class Ship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int LENGTH;

  @OneToMany(cascade = PERSIST)
  private List<BoardPosition> occupiedBoardPositions;

  private boolean isSunk;
  private Orientation orient;

  public Ship() {}

  public Ship(int length, Orientation orient, BoardPosition boardPosition) {
    LENGTH = length;
    occupiedBoardPositions = new ArrayList<>();
    occupiedBoardPositions.add(0, boardPosition);
    isSunk = false;
    this.orient = orient;
  }

  public int getLength() {
    return LENGTH;
  }

  public Orientation getOrient() {
    return orient;
  }

  public BoardPosition getOccupiedPosition(int anInt) {
    return occupiedBoardPositions.get(anInt);
  }

  public List<BoardPosition> getOccupiedBoardPositions() {
    return occupiedBoardPositions;
  }

  public void setOccupiedPosition(List<BoardPosition> occupiedBoardPosition) {
    this.occupiedBoardPositions = occupiedBoardPosition;
  }

  public boolean isSunk() {
    return isSunk;
  }

  public void setSunk(Boolean isSunk) {
    this.isSunk = isSunk;
  }

  @Override
  public boolean equals(Object ship) {
    Ship inputShip = (Ship) ship;
    return this.getClass().equals(inputShip.getClass());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.getClass());
  }
}
