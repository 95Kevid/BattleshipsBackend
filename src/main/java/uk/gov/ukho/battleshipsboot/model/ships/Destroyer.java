package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;

public class Destroyer extends Ship {
    public Destroyer(Orientation orient, BoardPosition boardPosition) {
        super(2, orient, boardPosition);
    }
}
