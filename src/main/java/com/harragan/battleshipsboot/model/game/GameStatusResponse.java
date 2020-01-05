package com.harragan.battleshipsboot.model.game;

import java.util.Set;

public class GameStatusResponse {
    private final Set<PlayerInGameInfo> playerInGameInfos;
    private final int playersTurnId;

    public GameStatusResponse(final int playersId, final Set<PlayerInGameInfo> playerInGameInfos) {
        this.playersTurnId = playersId;
        this.playerInGameInfos = playerInGameInfos;
    }

    public int getPlayersTurnId() {
        return playersTurnId;
    }

    public Set<PlayerInGameInfo> getPlayerInGameInfos() {
        return playerInGameInfos;
    }
}
