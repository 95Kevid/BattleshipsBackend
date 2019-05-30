package com.harragan.battleshipsboot.model.game;

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
  @OneToMany(cascade = ALL)
  private List<Player> players;

  private int noOfPlayers;

  private int gameArenaSize;

  public Game() {
  }

  public Game(final int noOfPlayers, final int gameArenaSize) {
    this.players = new LinkedList<>();
    this.noOfPlayers = noOfPlayers;
    this.gameArenaSize = gameArenaSize;
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
}
