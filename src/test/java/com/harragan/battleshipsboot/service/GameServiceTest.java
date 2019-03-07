package com.harragan.battleshipsboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady;
import com.harragan.battleshipsboot.repositorys.GameRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.Optional;

public class GameServiceTest {

    private Game game1;
    private Game game2;
    private Player player1;
    private Player player2;
    private Player player3;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
        game1 = new Game(2, 10);
        game2 = new Game(2, 10);

        player1 = new Player();
        player1.setId(1);
        player1.setGameArena(new GameArena(10));
        player2 = new Player();
        player2.setId(2);
        player2.setGameArena(new GameArena(10));
        player3 = new Player();
        player3.setId(3);
        player3.setGameArena(new GameArena(10));

        player1.setName("Burny");
        player2.setName("Helga");
        player3.setName("Fred");
    }

    @Test
    public void givenANumberOfPlayersWhenAGameIsInstantiatedAGameIdIsReturned() {
        game1.setId(12);
        when(gameRepository.save(any(Game.class))).thenReturn(game1);
        final int gameId1 = gameService.createGame(2, 10);
        verify(gameRepository, times(1)).save(any(Game.class));
        assertThat(gameId1).as("1rst run, the gameId should be 12").isEqualTo(12);

        game2.setId(189);
        when(gameRepository.save(any(Game.class))).thenReturn(game2);
        final int gameId2 = gameService.createGame(2, 10);
        verify(gameRepository, times(2)).save(any(Game.class));

        assertThat(gameId2).isEqualTo(189).as("2nd run gameId should be 189");
    }

    @Test
    public void givenAPlayerandAGameIdThenAPlayerIsAddedToAGame() {
        gameService = new GameService(gameRepository);
        when(gameRepository.findById(game1.getId())).thenReturn(Optional.of(game1));
        gameService.joinPlayerToGame(game1.getId(), player1);
        assertThat(game1.getPlayers().contains(player1)).isEqualTo(true);
    }

    @Test
    public void givenAGameIdAGameIsReturned() {
        final Game game = new Game(2, 10);
        game.setId(12);
        when(gameRepository.findById(14)).thenReturn(Optional.of(game));
        final Game returnedGame = gameService.getGame(14);
        assertThat(returnedGame)
                .as("The correct game that corresponds to " + "the Id of 14 should be returned")
                .isEqualTo(game);
    }

    @Test
    public void givenAGameIdThenReturnTheNumberOfPlayersToNumberOfNotReadyPlayers() {
        final LinkedList<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        player1.setReadyToStartGame(false);
        player2.setReadyToStartGame(true);
        player3.setReadyToStartGame(false);

        final Game game1 = new Game(3, 10);
        game1.setPlayers(players);
        game1.setId(9);

        when(gameRepository.findById(9)).thenReturn(Optional.of(game1));

        final PlayersToPlayersReady correctPlayersToPlayersNotReady = new PlayersToPlayersReady(3, 1);
        final PlayersToPlayersReady incorrectPlayersToPlayersNotReady = new PlayersToPlayersReady(3, 3);

        Assertions.assertThat(gameService.getPlayersToPlayersReady(game1.getId()))
                .isEqualTo(correctPlayersToPlayersNotReady)
                .as("1 of the 3 players should be ready");

        Assertions.assertThat(gameService.getPlayersToPlayersReady(game1.getId()))
                .isNotEqualTo(incorrectPlayersToPlayersNotReady)
                .as("3 of the 3 players should not be ready");
    }
}
