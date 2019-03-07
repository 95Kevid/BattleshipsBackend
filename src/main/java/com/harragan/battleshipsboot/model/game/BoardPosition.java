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

    public boolean equals(final Object object) {
        if (!(object instanceof BoardPosition)) {
            throw new IllegalArgumentException("Object of type BoardPosition should of been provided");
        }
        final BoardPosition input = (BoardPosition) object;
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
}
