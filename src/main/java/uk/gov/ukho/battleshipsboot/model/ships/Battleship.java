package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;

public class Battleship extends Ship {

    public Battleship(Orientation orient, BoardPosition boardPosition) {
        super(4, orient, boardPosition);

    }
}
