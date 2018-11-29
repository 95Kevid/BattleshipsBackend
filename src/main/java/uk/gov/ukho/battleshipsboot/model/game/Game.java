package uk.gov.ukho.battleshipsboot.model.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {
    private int id;
    private List<Player> players;

    public Game(int id) {
        this.players = new ArrayList<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
