package uk.gov.ukho.battleshipsboot.ships;

import uk.gov.ukho.battleshipsboot.main.Orientation;
import uk.gov.ukho.battleshipsboot.main.Position;

public class Carrier extends Ship {
    public Carrier(Orientation orient, Position position){
        super(5, orient, position);
    }
}
