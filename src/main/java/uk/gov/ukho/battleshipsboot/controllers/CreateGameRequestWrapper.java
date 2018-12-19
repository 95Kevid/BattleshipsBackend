package uk.gov.ukho.battleshipsboot.controllers;

public class CreateGameRequestWrapper {
    private Integer numberOfPlayers;

    public CreateGameRequestWrapper(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
