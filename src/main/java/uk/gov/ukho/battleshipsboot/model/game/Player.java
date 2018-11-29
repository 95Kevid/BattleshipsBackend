package uk.gov.ukho.battleshipsboot.model.game;

import uk.gov.ukho.battleshipsboot.model.ships.Ship;

import java.util.List;

public class Player {
    private GameArena gameArena;

    public List<Ship> getShipsOnBoard() {
        return gameArena.getShipsOnBoard();
    }
}
