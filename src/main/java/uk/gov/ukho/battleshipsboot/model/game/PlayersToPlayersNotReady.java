package uk.gov.ukho.battleshipsboot.model.game;

import java.util.Objects;

public class PlayersToPlayersNotReady {
    private final int playersInGame;
    private final int playersNotReady;

    public PlayersToPlayersNotReady(int playersInGame, int playersNotReady) {
        this.playersInGame = playersInGame;
        this.playersNotReady = playersNotReady;
    }

    public int getPlayersInGame() {
        return playersInGame;
    }

    public int getPlayersNotReady() {
        return playersNotReady;
    }

    @Override
    public boolean equals(Object object) {
        PlayersToPlayersNotReady input = (PlayersToPlayersNotReady) object;
        return playersInGame == input.playersInGame
                && playersNotReady == input.playersNotReady;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playersInGame, playersNotReady);
    }
}
