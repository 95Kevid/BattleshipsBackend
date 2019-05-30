package com.harragan.battleshipsboot.facades;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.harragan.battleshipsboot.model.kotlinmodel.game.ShootRequest;
import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.service.BoardPositionFactory;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import com.harragan.battleshipsboot.service.exceptions.IllegalShotException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

public class ShootingFacadeTest {

  private Player player1;
  private Player player2;
  private Player player3;
  private Game game;
  private LinkedList<Player> players;
  @Mock
  private GameArenaService gameArenaService;
  @Mock
  private GameService gameService;
  private ShootingFacade shootingFacade;
  @Mock
  private PlayerService playerService;

  @Before
  public void initTests() {
    MockitoAnnotations.initMocks(this);
    game = new Game(2, 10);
    game.setId(1);
    shootingFacade = new ShootingFacade(gameService, gameArenaService, playerService);
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
    players = new LinkedList<>();
    players.add(player1);
    players.add(player2);
    players.add(player3);
    players.forEach(player -> player.setReadyToStartGame(true));
  }

  @Test
  public void whenAPlayerIdAnGameIdAndBoardPositionIsProvidedThenTheCorrespondingBoardPositionIsFlaggedAsHitForEveryPlayerApartFromThePlayerCorrespondingToThePlayerIdProvided() {
    when(gameService.getGame(1)).thenReturn(game);
    when(playerService.getPlayerById(1)).thenReturn(player1);
    when(gameService.checkForTurn(1)).thenReturn(player1);
    BoardPosition positionA1 = BoardPositionFactory.createBoardPosition('A', 1);
    BoardPosition positionC4 = BoardPositionFactory.createBoardPosition('C', 4);

    game.setPlayers(players);
    ShootRequest shootRequestA1 = new ShootRequest(1, 1, positionA1);
    ShootRequest shootRequestC4 = new ShootRequest(1, 1, positionC4);
    shootingFacade.shootPosition(shootRequestA1);
    shootingFacade.shootPosition(shootRequestC4);

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

  @Test
  public void whenAPlayerIsProvidedWithinTheShootRequestWhosTurnItIsNotThenAnIllegalShotExceptionIsThrown() {
    when(gameService.getGame(1)).thenReturn(game);
    when(gameService.checkForTurn(1)).thenReturn(player2);
    when(playerService.getPlayerById(1)).thenReturn(player1);
    BoardPosition positionA1 = BoardPositionFactory.createBoardPosition('A', 1);
    game.setPlayers(players);
    gameService.nextTurn(game);
    ShootRequest shootRequestA1 = new ShootRequest(1, 1, positionA1);

    assertThatExceptionOfType(IllegalShotException.class)
        .isThrownBy(() -> shootingFacade.shootPosition(shootRequestA1));
  }

  @Test
  public void whenPlayerTakesAShotThenTheTurnIsPassedToTheNextPlayer() {
    when(gameService.getGame(1)).thenReturn(game);
    when(gameService.checkForTurn(1)).thenReturn(player1);
    when(playerService.getPlayerById(1)).thenReturn(player1);
    BoardPosition positionA1 = BoardPositionFactory.createBoardPosition('A', 1);
    game.setPlayers(players);
    ShootRequest shootRequestA1 = new ShootRequest(1, 1, positionA1);
    shootingFacade.shootPosition(shootRequestA1);

    verify(gameService, times(1)).nextTurn(game);
  }

  @Test
  public void whenSomeOfThePlayersAreNotReadyToStartAndAnAttemptIsMadeToMakeAShotThenAnIllegalShotExceptionIsThrown() {
    when(gameService.getGame(1)).thenReturn(game);
    when(gameService.checkForTurn(1)).thenReturn(player1);
    when(playerService.getPlayerById(2)).thenReturn(player2);
    BoardPosition positionA1 = BoardPositionFactory.createBoardPosition('A', 1);
    player1.setReadyToStartGame(false);
    player2.setReadyToStartGame(true);
    player3.setReadyToStartGame(true);
    game.setPlayers(players);
    ShootRequest shootRequestA1 = new ShootRequest(2, 1, positionA1);

    assertThatExceptionOfType(IllegalShotException.class)
        .isThrownBy(() -> shootingFacade.shootPosition(shootRequestA1));
  }

  @Test
  public void givenAPositionThatHasAlreadyBeenHitWhenThatPositionIsShotAtAgainThenAnIllegalShotExceptionIsThrown() {
    when(gameService.getGame(1)).thenReturn(game);
    when(gameService.checkForTurn(1)).thenReturn(player1);
    when(playerService.getPlayerById(2)).thenReturn(player2);
    BoardPosition positionD4 = BoardPositionFactory.createBoardPosition('D', 4);
    positionD4.setHit(true);
    players.forEach(player -> player.setReadyToStartGame(true));
    game.setPlayers(players);
    ShootRequest shootRequestD4 = new ShootRequest(2, 1, positionD4);

    assertThatExceptionOfType(IllegalShotException.class)
        .isThrownBy(() -> shootingFacade.shootPosition(shootRequestD4))
        .withMessage("This position has already been hit.");
  }
}
