package uk.gov.ukho.battleshipsboot.facades;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.repositorys.PlayerRepository;
import uk.gov.ukho.battleshipsboot.service.GameService;
import uk.gov.ukho.battleshipsboot.service.PlayerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PlayerAddingFacadeTest {

    private PlayerAddingFacade playerAddingFacade;

    @Mock
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock GameRepository gameRepository;

    @Mock
    private GameService gameService;

    @Mock
    private Player burny;

    private Player helga;

    private Game game;

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
        playerAddingFacade = new PlayerAddingFacade(playerService,  gameService
                ,playerRepository,  gameRepository);
        game = new Game();
        game.setId(1);
    }

    @Test
    public void givenWhenAPlayerNameAndAGameIdIsProvidedAPlayerIsCreated() {
        String playerName = "Burny";
        when(playerService.createPlayer(playerName, playerRepository)).thenReturn(burny);

        playerAddingFacade.createPlayerAndJoinToGame(playerName, game.getId());

        verify(playerService, times(1)).createPlayer(playerName, playerRepository);
        verify(gameService, times(1)).joinPlayerToGame(1, burny);
        playerAddingFacade.createPlayerAndJoinToGame(playerName, game.getId());
    }

}
