package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.Position;

public class Carrier extends Ship {
    public Carrier(Orientation orient, Position position){
        super(5, orient, position);
    }
}
