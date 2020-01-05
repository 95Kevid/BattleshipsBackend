package com.harragan.battleshipsboot.model.game;

import com.harragan.battleshipsboot.model.kotlinmodel.game.GameStatus;

import static javax.persistence.CascadeType.ALL;


import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OrderColumn
  @OneToMany
  private List<Player> players;

  private int noOfPlayers;

  private int gameArenaSize;

  private GameStatus gameStatus;

  public Game() {
  }

  public Game(final int noOfPlayers, final int gameArenaSize, final GameStatus gameStatus) {
    this.players = new LinkedList<>();
    this.noOfPlayers = noOfPlayers;
    this.gameArenaSize = gameArenaSize;
    this.gameStatus = gameStatus;
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public LinkedList<Player> getPlayers() {
    return new LinkedList<>(players);
  }

  public void setPlayers(final LinkedList<Player> players) {
    this.players = players;
  }

  public int getMaxPlayers() {
    return noOfPlayers;
  }

  public int getGameArenaSize() {
    return gameArenaSize;
  }

  public GameStatus getStatus() {
    return gameStatus;
  }

  public void setGameStatus(final GameStatus gameStatus) {
    this.gameStatus = gameStatus;
  }
}
