package com.harragan.battleshipsboot.model.game;

import java.util.Objects;

public class PlayersToPlayersReady {
    private final int playersInGame;
    private final int playersReady;

    public PlayersToPlayersReady(int playersInGame, int playersReady) {
        this.playersInGame = playersInGame;
        this.playersReady = playersReady;
    }

    public int getPlayersInGame() {
        return playersInGame;
    }

    public int getPlayersReady() {
        return playersReady;
    }

    @Override
    public boolean equals(Object object) {
        PlayersToPlayersReady input = (PlayersToPlayersReady) object;
        return playersInGame == input.playersInGame
                && playersReady == input.playersReady;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playersInGame, playersReady);
    }
}
