package com.harragan.battleshipsboot.facades;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady;
import com.harragan.battleshipsboot.repositorys.GameRepository;
import com.harragan.battleshipsboot.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PollingFacadeTest {

  private PollingFacade pollingFacade;

  @Mock
  private GameService gameService;

  @Mock
  private GameRepository gameRepository;

  @Before
  public void initTest() {
    MockitoAnnotations.initMocks(this);
    pollingFacade = new PollingFacade(gameService);
  }

  @Test
  public void
  givenWhenAGameIdIsProvidedThenANumberOfPlayersInTheGameIsProvidedWithANumberOfPlayersThatAreNotReady() {
    final PlayersToPlayersReady expectedPlayersToPlayersNotReady = new PlayersToPlayersReady(3, 2);

    when(gameService.getPlayersToPlayersReady(1)).thenReturn(expectedPlayersToPlayersNotReady);

    final PlayersToPlayersReady actualPlayersToPlayersNotReady =
        pollingFacade.getNumberOfNotReadyPlayersToReadyPlayers(1);

    assertThat(expectedPlayersToPlayersNotReady).isEqualTo(actualPlayersToPlayersNotReady);
  }
}
