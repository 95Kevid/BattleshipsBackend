package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.repositorys.GameRepository;
import com.harragan.battleshipsboot.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady;


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
        PlayersToPlayersReady expectedPlayersToPlayersNotReady
                = new PlayersToPlayersReady(3,2);

        when(gameService.getPlayersToPlayersReady(1))
                .thenReturn(expectedPlayersToPlayersNotReady);

        PlayersToPlayersReady actualPlayersToPlayersNotReady
                = pollingFacade.getNumberOfNotReadyPlayersToReadyPlayers(1);

        assertThat(expectedPlayersToPlayersNotReady).isEqualTo(actualPlayersToPlayersNotReady);
    }
}
