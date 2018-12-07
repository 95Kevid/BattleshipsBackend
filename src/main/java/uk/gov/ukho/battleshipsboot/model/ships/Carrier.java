package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;

public class Carrier extends Ship {
    public Carrier(Orientation orient, BoardPosition boardPosition){
        super(5, orient, boardPosition);
    }
}
