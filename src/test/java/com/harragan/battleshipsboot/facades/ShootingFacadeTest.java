package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.repositorys.GameRepository;
import com.harragan.battleshipsboot.service.BoardPositionFactory;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

public class ShootingFacadeTest {

  private Player player1;
  private Player player2;
  private Player player3;
  private Game game;
  @Mock
  private GameArenaService gameArenaService;
  @Mock
  private GameService gameService;
  private ShootingFacade shootingFacade;
  @Mock
  private GameRepository gameRepository;

  @Before
  public void initTests() {
    MockitoAnnotations.initMocks(this);
    game = new Game(2, 10);
    shootingFacade = new ShootingFacade(gameService, gameArenaService);
    player1 = new Player();
    player1.setId(1);
    player1.setGameArena(new GameArena(10));
    player2 = new Player();
    player2.setId(2);
    player2.setGameArena(new GameArena(10));
    player3 = new Player();
    player3.setId(3);
    player3.setGameArena(new GameArena(10));
    player1.setName("Burny");
    player2.setName("Helga");
    player3.setName("Fred");
  }

  @Test
  public void whenAPlayerIdAnGameIdAndBoardPositionIsProvidedThenTheCorrespondingBoardPositionIsFlaggedAsHitForEveryPlayerApartFromThePlayerCorrespondingToThePlayerIdProvided() {
    when(gameService.getGame(1)).thenReturn(game);
    BoardPosition positionA1 = BoardPositionFactory.createBoardPosition('A', 1);
    BoardPosition positionC4 = BoardPositionFactory.createBoardPosition('C', 4);
    LinkedList<Player> players = new LinkedList<>();
    players.add(player1);
    players.add(player2);
    players.add(player3);
    game.setPlayers(players);
    shootingFacade.shootPosition(1, 1, positionA1);
    shootingFacade.shootPosition(1, 1, positionC4);

    GameArena gameArena1 = player1.getGameArena();
    GameArena gameArena2 = player2.getGameArena();
    GameArena gameArena3 = player3.getGameArena();

    verify(gameArenaService, times(1)).registerHit(positionA1, gameArena2);
    verify(gameArenaService, times(1)).registerHit(positionC4, gameArena2);
    verify(gameArenaService, times(1)).registerHit(positionA1, gameArena3);
    verify(gameArenaService, times(1)).registerHit(positionC4, gameArena3);
    verify(gameArenaService, never()).registerHit(positionA1, gameArena1);
    verify(gameArenaService, never()).registerHit(positionC4, gameArena1);
  }
}
