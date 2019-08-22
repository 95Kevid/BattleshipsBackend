package com.harragan.battleshipsboot.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class Player {

  @JsonIgnore
  @OneToOne(cascade = CascadeType.ALL)
  private GameArena gameArena;

  @JsonIgnore
  private boolean readyToStartGame;

  private String playerName;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private boolean loser;

  private boolean winner;

  public Player() {
  }

  public Player(final String playerName) {
    this.playerName = playerName;
  }

  public String getName() {
    return playerName;
  }

  public void setName(final String playerName) {
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

  public boolean isLoser() {
    return loser;
  }

  public void setLoser(boolean loser) {
    this.loser = loser;
  }

  public boolean isWinner() {
      return winner;
  }

  public void setWinner(boolean winner) {
      this.winner = winner;
  }
}
