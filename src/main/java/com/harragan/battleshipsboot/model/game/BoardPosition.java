package com.harragan.battleshipsboot.model.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class BoardPosition {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private int id;

  private char col;
  private int row;
  private boolean isHit;

  public BoardPosition() {}

  public BoardPosition(char col, int inputRow) {
    this.col = Character.toUpperCase(col);
    this.row = inputRow;
    isHit = false;
  }

  public char getCol() {
    return col;
  }

  public void setCol(char col) {
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public boolean equals(Object object) {
    if (!(object instanceof BoardPosition)) {
      throw new IllegalArgumentException("Object of type BoardPosition should of been provided");
    }
    BoardPosition input = (BoardPosition) object;
    return this.col == input.getCol() && this.row == input.getRow();
  }

  public int hashCode() {
    return Objects.hash(col, row);
  }

  public void setHit() {
    isHit = true;
  }

  public boolean isHit() {
    return isHit;
  }

  public void setHit(boolean hit) {
    isHit = hit;
  }
}
