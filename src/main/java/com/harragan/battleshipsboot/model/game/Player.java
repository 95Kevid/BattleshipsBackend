package com.harragan.battleshipsboot.model.game;

import javax.persistence.*;

@Entity
public class Player {

  @OneToOne(cascade = CascadeType.PERSIST)
  private GameArena gameArena;

  private String playerName;
  private boolean readyToStartGame;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  public Player() {}

  public Player(String playerName) {
    this.playerName = playerName;
  }

  public boolean isReadyToStartGame() {
    return readyToStartGame;
  }

  public void setReadyToStartGame(boolean readyToStartGame) {
    this.readyToStartGame = readyToStartGame;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public GameArena getGameArena() {
    return gameArena;
  }

  public void setGameArena(GameArena gameArena) {
    this.gameArena = gameArena;
  }

  public void setName(String name) {
    this.playerName = name;
  }
}
