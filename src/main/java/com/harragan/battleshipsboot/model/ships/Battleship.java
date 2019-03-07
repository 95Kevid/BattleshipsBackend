package com.harragan.battleshipsboot.model.ships;

import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.model.game.Orientation;

import javax.persistence.Entity;

@Entity
public class Battleship extends Ship {

    public Battleship(final Orientation orient, final BoardPosition boardPosition) {
        super(4, orient, boardPosition);
    }

    public Battleship() {
    }
}
