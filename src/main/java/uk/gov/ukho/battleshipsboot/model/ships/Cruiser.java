package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;

public class Cruiser extends Ship {
    public Cruiser(Orientation orient, BoardPosition boardPosition) {
        super(3, orient, boardPosition);
    }
}
