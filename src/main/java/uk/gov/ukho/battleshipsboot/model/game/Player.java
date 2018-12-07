package uk.gov.ukho.battleshipsboot.model.game;

import javax.persistence.*;


@Entity
public class Player {

    @OneToOne
    private GameArena gameArena;

    private String playerName;

    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Player() {
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player(int anId) {
        this.id = anId;
    }

    public GameArena getGameArena() {return gameArena;}

    public void setGameArena(GameArena gameArena) {
        this.gameArena = gameArena;
    }

}
