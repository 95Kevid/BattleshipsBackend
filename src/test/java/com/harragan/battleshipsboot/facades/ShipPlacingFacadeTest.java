package com.harragan.battleshipsboot.facades;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Orientation;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.ships.Destroyer;
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
        shipPlacingFacade = new ShipPlacingFacade(gameArenaService, gameService, playerService);
    }

    @Test
    public void givenAShipPositionGameIDAndPlayerIdThenAShipIsPositionedOnThatPlayersGameArena() {
        Game game = new Game();
        Destroyer destroyer = new Destroyer(Orientation.HORIZONTAL, new BoardPosition('A', 1));
        game.setId(1);

        when(gameService.getGame(1)).thenReturn(game);
        when(burney.getGameArena()).thenReturn(gameArena);
        when(playerService.getPlayerById(1)).thenReturn(burney);

        shipPlacingFacade.placeShip(1, game.getId(), destroyer);

        verify(gameArenaService, times(1)).addShip(destroyer, gameArena);
        verify(gameService, times(1)).getGame(1);
    }
}
