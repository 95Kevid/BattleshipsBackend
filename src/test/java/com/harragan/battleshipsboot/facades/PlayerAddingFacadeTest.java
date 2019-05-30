package com.harragan.battleshipsboot.facades;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.harragan.battleshipsboot.model.kotlinmodel.game.JoinGameResponse;
import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlayerAddingFacadeTest {

  private PlayerAddingFacade playerAddingFacade;
  @Mock
  private PlayerService playerService;
  @Mock
  private PlayerRepository playerRepository;
  private GameArena gameArena;
  @Mock
  private GameService gameService;
  private Player burny;

  @Mock
  private GameArenaService gameArenaService;

  private Game game;

  @Before
  public void initTest() {
    MockitoAnnotations.initMocks(this);
    playerAddingFacade =
        new PlayerAddingFacade(
            playerService, gameService, playerRepository, gameArenaService);
    game = new Game(2, 15);
    game.setId(1);
    burny = new Player("Burny");
    burny.setId(1);
    gameArena = new GameArena(15);
    burny.setGameArena(gameArena);
  }

  @Test
  public void givenWhenAPlayerNameAndAGameIdIsProvidedAPlayerIsCreated() {
    final String playerName = "Burny";
    when(playerService.createPlayer(playerName, playerRepository)).thenReturn(burny);
    when(gameService.getGame(1)).thenReturn(game);

    playerAddingFacade.createPlayerAndJoinToGame(playerName, game.getId());

    verify(playerService, times(1)).createPlayer(playerName, playerRepository);
    verify(gameService, times(1)).joinPlayerToGame(1, burny);
    playerAddingFacade.createPlayerAndJoinToGame(playerName, game.getId());
  }

  @Test
  public void whenSuppliedPlayerNameAndGameIdThenAPlayerIsCreatedAndThenIsAddedToAGameAndJoinGameResponseIsReturnedContainingPlayerIdAndGameIdAndGridSize() {
    final String playerName = "Burny";
    when(gameService.getGame(1)).thenReturn(game);
    when(playerService.createPlayer(playerName, playerRepository)).thenReturn(burny);

    JoinGameResponse joinGameResponse = playerAddingFacade.createPlayerAndJoinToGame(playerName, game.getId());

    assertThat(joinGameResponse.getPlayerId()).isEqualTo(1);
    assertThat(joinGameResponse.getGameId()).isEqualTo(1);
    assertThat(joinGameResponse.getGridSize()).isEqualTo(15);
  }
}
