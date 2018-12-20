package com.harragan.battleshipsboot.model.ships;

import com.harragan.battleshipsboot.model.game.Orientation;
import com.harragan.battleshipsboot.model.game.BoardPosition;

public class Carrier extends Ship {
    public Carrier(Orientation orient, BoardPosition boardPosition){
        super(5, orient, boardPosition);
    }
}
