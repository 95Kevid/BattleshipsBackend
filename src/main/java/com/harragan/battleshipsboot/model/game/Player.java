package com.harragan.battleshipsboot.model.game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Player {

    @OneToOne(cascade = CascadeType.PERSIST)
    private GameArena gameArena;

    private String playerName;
    private boolean readyToStartGame;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Player() {
    }

    public Player(final String playerName) {
        this.playerName = playerName;
    }

    public boolean isReadyToStartGame() {
        return readyToStartGame;
    }

    public void setReadyToStartGame(final boolean readyToStartGame) {
        this.readyToStartGame = readyToStartGame;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public GameArena getGameArena() {
        return gameArena;
    }

    public void setGameArena(final GameArena gameArena) {
        this.gameArena = gameArena;
    }

    public void setName(final String name) {
        this.playerName = name;
    }
}
