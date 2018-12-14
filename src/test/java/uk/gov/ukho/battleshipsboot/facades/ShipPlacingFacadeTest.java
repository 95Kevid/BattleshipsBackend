package uk.gov.ukho.battleshipsboot.facades;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import uk.gov.ukho.battleshipsboot.model.game.*;
import uk.gov.ukho.battleshipsboot.model.ships.Column;
import uk.gov.ukho.battleshipsboot.model.ships.Destroyer;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.service.GameService;
import uk.gov.ukho.battleshipsboot.service.PlayerService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ShipPlacingFacadeTest {

    private ShipPlacingFacade shipPlacingFacade;

    @Mock
    private GameRepository gameRepository;

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
        shipPlacingFacade = new ShipPlacingFacade(gameRepository, gameService, playerService);
    }

    @Test
    public void givenAShipPositionGameIDAndPlayerIdThenAShipIsPositionedOnThatPlayersGameArena() {
        Game game = new Game();
        Destroyer destroyer = new Destroyer(Orientation.HORIZONTAL, new BoardPosition(Column.A, 1));
        game.setId(1);

        when(gameService.getGame(1)).thenReturn(game);
        when(burney.getGameArena()).thenReturn(gameArena);
        when(playerService.getPlayerById(1)).thenReturn(burney);

        shipPlacingFacade.placeShip(1, game.getId(), destroyer);

        verify(gameArena, times(1)).addShip(destroyer);
        verify(gameService, times(1)).getGame(1);

    }
}
