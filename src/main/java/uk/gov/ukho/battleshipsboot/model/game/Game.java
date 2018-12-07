package uk.gov.ukho.battleshipsboot.model.game;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = PERSIST)
    private List<Player> players;

    public Game() {
        this.players = new ArrayList<>();
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

    public void printId() {
        System.out.println(id);
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
