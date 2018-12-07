package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;

public class Submarine extends Ship {
    public Submarine(Orientation orient, BoardPosition boardPosition) {
        super(3, orient, boardPosition);
    }
}
