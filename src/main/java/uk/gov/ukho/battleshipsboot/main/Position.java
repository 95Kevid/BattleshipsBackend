package uk.gov.ukho.battleshipsboot.main;

import java.util.Objects;

public class Position {
    private Column col;
    private int row;

    public Position(Column col, int row) {
        if(row > 10){
            throw new IllegalArgumentException("The row should not be greater than 10.");
        }
        this.col = col;
        this.row = row;
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
}
