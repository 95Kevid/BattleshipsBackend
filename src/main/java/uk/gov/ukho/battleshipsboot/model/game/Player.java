package uk.gov.ukho.battleshipsboot.model.game;

import javax.persistence.*;

@Entity
public class Player {

    @OneToOne(cascade = CascadeType.PERSIST)
    private GameArena gameArena;

    private String playerName;

    public boolean isReadyToStartGame() {
        return readyToStartGame;
    }

    public void setReadyToStartGame(boolean readyToStartGame) {
        this.readyToStartGame = readyToStartGame;
    }

    private boolean readyToStartGame;

    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Player() {
        gameArena = new GameArena();
    }

    public Player(String playerName) {
        this.playerName = playerName;
        this.gameArena = new GameArena();
    }

    public GameArena getGameArena() {return gameArena;}

    public void setGameArena(GameArena gameArena) {
        this.gameArena = gameArena;
    }

    public void setName(String name) {
        this.playerName = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
