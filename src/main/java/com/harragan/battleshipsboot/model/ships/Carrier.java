package com.harragan.battleshipsboot.model.ships;

import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.model.game.Orientation;

import javax.persistence.Entity;

@Entity
public class Carrier extends Ship {
    public Carrier(final Orientation orient, final BoardPosition boardPosition) {
        super(5, orient, boardPosition);
    }

    public Carrier() {
    }
}
