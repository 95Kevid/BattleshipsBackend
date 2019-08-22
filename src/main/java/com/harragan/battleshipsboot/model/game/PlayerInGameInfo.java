package com.harragan.battleshipsboot.model.game;

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;

import java.util.Set;

public class PlayerInGameInfo {
    private final int playerId;
    private final String name;
    private final Set<BoardPosition> shotPositions;
    private final Set<Ship> sunkShips;
    private final boolean loser;
    private final boolean winner;

    public PlayerInGameInfo(final int playerId, final String name, final Set<BoardPosition> shotPositions,
                            final Set<Ship> sunkShips, final boolean isLoser, final boolean isWinner) {
        this.playerId = playerId;
        this.name = name;
        this.shotPositions = shotPositions;
        this.sunkShips = sunkShips;
        this.loser = isLoser;
        this.winner = isWinner;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Set<BoardPosition> getShotPositions() {
        return shotPositions;
    }

    public Set<Ship> getSunkShips() {
        return sunkShips;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isLoser() {
        return loser;
    }
}
