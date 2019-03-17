package com.harragan.battleshipsboot.model.game;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BoardPosition {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private char col;
    private int row;
    private boolean isHit;

    public BoardPosition() {
    }

    public BoardPosition(final char col, final int inputRow) {
        this.col = Character.toUpperCase(col);
        this.row = inputRow;
        isHit = false;
    }

    public char getCol() {
        return col;
    }

    public void setCol(final char col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(final int row) {
        this.row = row;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BoardPosition that = (BoardPosition) o;
        return col == that.col &&
                row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, col, row, isHit);
    }

    public void setHit() {
        isHit = true;
    }

    public boolean isHit() {
        return isHit;
    }
}
