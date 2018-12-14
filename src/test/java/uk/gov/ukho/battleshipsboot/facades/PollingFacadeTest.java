package uk.gov.ukho.battleshipsboot.facades;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import uk.gov.ukho.battleshipsboot.model.game.PlayersToPlayersNotReady;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.service.GameService;


public class PollingFacadeTest {

    private PollingFacade pollingFacade;

    @Mock
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
        pollingFacade = new PollingFacade(gameService, gameRepository);
    }

    @Test
    public void givenWhenAGameIdIsProvidedThenANumberOfPlayersInTheGameIsProvidedWithANumberOfPlayersThatAreNotReady() {
        PlayersToPlayersNotReady expectedPlayersToPlayersNotReady
                = new PlayersToPlayersNotReady(3,2);

        when(gameService.getNumberOfNotReadyPlayersToReadyPlayers(1))
                .thenReturn(expectedPlayersToPlayersNotReady);

        PlayersToPlayersNotReady actualPlayersToPlayersNotReady
                = pollingFacade.getNumberOfNotReadyPlayersToReadyPlayers(1);

        assertThat(expectedPlayersToPlayersNotReady).isEqualTo(actualPlayersToPlayersNotReady);
    }
}
