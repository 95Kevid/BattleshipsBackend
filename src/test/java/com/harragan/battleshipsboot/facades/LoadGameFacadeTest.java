package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.GameStatus;
import com.harragan.battleshipsboot.model.kotlinmodel.game.LoadGameRequest;
import com.harragan.battleshipsboot.model.kotlinmodel.game.LoadGameResponse;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LoadGameFacadeTest {
    @Mock
    private PlayerService playerService;
    @Mock
    private GameService gameService;
    private LoadGameFacade loadGameFacade;
    private LoadGameRequest loadGameRequest =  new LoadGameRequest(1, 1);
    @Mock
    private Game game1;
    @Mock
    private Player player1;
    @Mock
    private GameArena gameArena;

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
        loadGameFacade = new LoadGameFacade(gameService, playerService);
    }

    @Test
    public void givenALoadGameRequestThenALoadGameResponseIsReturnedContainingAGameStatusAndPlayerAndTheirGameArena() {
        when(gameService.getGame(1)).thenReturn(game1);
        when(game1.getStatus()).thenReturn(GameStatus.JOIN_GAME);
        when(playerService.getPlayerById(1)).thenReturn(player1);
        when(player1.getGameArena()).thenReturn(gameArena);
        LoadGameResponse loadGameResponseExpected =  new LoadGameResponse(1, player1, GameStatus.JOIN_GAME, player1.getGameArena());
        LoadGameResponse loadGameResponseReturned = loadGameFacade.loadGame(loadGameRequest);
        assertThat(loadGameResponseReturned).isEqualTo(loadGameResponseExpected);
    }
}
