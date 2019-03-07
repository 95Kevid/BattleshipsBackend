package com.harragan.battleshipsboot.facades;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.repositorys.GameRepository;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlayerAddingFacadeTest {

    @Mock
    GameRepository gameRepository;
    private PlayerAddingFacade playerAddingFacade;
    @Mock
    private PlayerService playerService;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private GameService gameService;

    @Mock
    private Player burny;

    @Mock
    private GameArenaService gameArenaService;

    private Game game;

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
        playerAddingFacade =
                new PlayerAddingFacade(
                        playerService, gameService, playerRepository, gameRepository, gameArenaService);
        game = new Game(2, 10);
        game.setId(1);
    }

    @Test
    public void givenWhenAPlayerNameAndAGameIdIsProvidedAPlayerIsCreated() {
        String playerName = "Burny";
        when(playerService.createPlayer(playerName, playerRepository)).thenReturn(burny);
        when(gameService.getGame(1)).thenReturn(game);

        playerAddingFacade.createPlayerAndJoinToGame(playerName, game.getId());

        verify(playerService, times(1)).createPlayer(playerName, playerRepository);
        verify(gameService, times(1)).joinPlayerToGame(1, burny);
        playerAddingFacade.createPlayerAndJoinToGame(playerName, game.getId());
    }
}
