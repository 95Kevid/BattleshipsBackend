package uk.gov.ukho.battleshipsboot.controllers;

public class PlayerRequestWrapper {
    private int gameId;
    private String playerName;

    public PlayerRequestWrapper(int gameId, String playerName) {
        this.gameId = gameId;
        this.playerName = playerName;
    }

    public int getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
