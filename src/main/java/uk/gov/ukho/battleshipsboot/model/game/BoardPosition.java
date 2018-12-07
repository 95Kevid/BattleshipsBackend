package uk.gov.ukho.battleshipsboot.model.game;

import uk.gov.ukho.battleshipsboot.model.ships.Column;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BoardPosition {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private Column col;
    private int row;
    private boolean isHit;

    public BoardPosition() {

    }
    public BoardPosition(Column col, int row) {
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

    public int getRow() {
        return row;
    }

    public void setCol(Column col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean equals(Object object) {
        if(!(object instanceof BoardPosition)) {
            throw new IllegalArgumentException("Object of type BoardPosition should of been proviced");
        }
        BoardPosition input = (BoardPosition) object;
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
