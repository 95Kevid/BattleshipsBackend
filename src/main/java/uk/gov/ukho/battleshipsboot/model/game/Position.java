package uk.gov.ukho.battleshipsboot.model.game;

import uk.gov.ukho.battleshipsboot.model.ships.Column;

import java.util.Objects;

public class Position {
    private Column col;
    private int row;
    private boolean isHit;

    public Position(Column col, int row) {
        if(row > 10){
            throw new IllegalArgumentException("The row should not be greater than 10.");
        }
        this.col = col;
        this.row = row;
        isHit = false;
    }

    public Column getCol() {
        return col;
    }

    public void setCol(Column col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean equals(Object object) {
        if(!(object instanceof Position)) {
            throw new IllegalArgumentException("Object of type Position should of been proviced");
        }
        Position input = (Position) object;
        return this.col == input.getCol() && this.row == input.getRow();
    }

    public int hashCode() {
        return Objects.hash(col, row);
    }

    public void setHit(){
        isHit = true;
    }

    public boolean isHit() {
        return isHit;
    }

}
