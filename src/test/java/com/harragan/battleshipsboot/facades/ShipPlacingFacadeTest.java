package com.harragan.battleshipsboot.facades;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.ShipType;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ShipPlacingFacadeTest {

  private ShipPlacingFacade shipPlacingFacade;

  @Mock
  private GameArenaService gameArenaService;

  @Mock
  private GameService gameService;

  @Mock
  private PlayerService playerService;

  @Mock
  private GameArena gameArena;

  @Mock
  private Player burney;

  @Before
  public void initTest() {
    MockitoAnnotations.initMocks(this);
    shipPlacingFacade = new ShipPlacingFacade(gameArenaService, playerService);
  }

  @Test
  public void givenAShipPositionGameIdAndPlayerIdThenAShipIsPositionedOnThatPlayersGameArena() {
    final Game game = new Game();
    final Ship destroyer =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 1), ShipType.DESTROYER);
    game.setId(1);

    when(burney.getGameArena()).thenReturn(gameArena);
    when(playerService.getPlayerById(1)).thenReturn(burney);

    shipPlacingFacade.placeShip(1, game.getId(), destroyer);

    verify(gameArenaService, times(1)).addShip(destroyer, gameArena);
  }
}
