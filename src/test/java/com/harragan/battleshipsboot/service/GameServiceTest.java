package com.harragan.battleshipsboot.service;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady;
import com.harragan.battleshipsboot.repositorys.GameRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.harragan.battleshipsboot.model.game.Player;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class GameServiceTest {

    private GameService gameService;
    private Game game1;
    private Game game2;
    private Player player1;
    private Player player2;
    private Player player3;

    @Mock
    private GameRepository gameRepository;

    @Before
    public void initTest() {
       MockitoAnnotations.initMocks(this);
       gameService = new GameService(gameRepository);
       game1 = new Game(2);
       game2 = new Game(2);

       player1 = new Player();
       player1.setId(1);
       player2 = new Player();
       player2.setId(2);
       player3 = new Player();
       player3.setId(3);

       player1.setName("Burny");
       player2.setName("Helga");
       player3.setName("Fred");
    }

    @Test
    public void givenANumberOfPlayersWhenAGameIsInstantiatedAGameIDIsReturned() {
        game1.setId(12);
        when(gameRepository.save(any(Game.class))).thenReturn(game1);
        int gameId1 = gameService.createGame(2);
        verify(gameRepository, times(1)).save(any(Game.class));
        assertThat(gameId1).as("1rst run, the gameId should be 12").isEqualTo(12);

        game2.setId(189);
        when(gameRepository.save(any(Game.class))).thenReturn(game2);
        int gameId2 = gameService.createGame(2);
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
        Game game = new Game(2);
        game.setId(12);
        when(gameRepository.findById(14)).thenReturn(Optional.of(game));
        Game returnedGame = gameService.getGame(14);
        assertThat(returnedGame).as("The correct game that corresponds to " +
                "the Id of 14 should be returned").isEqualTo(game);
    }

    @Test
    public void givenAGameIdThenReturnTheNumberOfPlayersToNumberOfNotReadyPlayers() {
        LinkedList<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        player1.setReadyToStartGame(false);
        player2.setReadyToStartGame(true);
        player3.setReadyToStartGame(false);

        Game game1 = new Game();
        game1.setPlayers(players);
        game1.setId(9);

        when(gameRepository.findById(9)).thenReturn(Optional.of(game1));

        PlayersToPlayersReady correctPlayersToPlayersNotReady
                = new PlayersToPlayersReady(3,1);
        PlayersToPlayersReady incorrectPlayersToPlayersNotReady
                = new PlayersToPlayersReady(3,3);

        Assertions.assertThat(gameService.getPlayersToPlayersReady(game1.getId()))
                .isEqualTo(correctPlayersToPlayersNotReady)
                .as("2 of the 3 players should be ready");

        Assertions.assertThat(gameService.getPlayersToPlayersReady(game1.getId()))
                .isNotEqualTo(incorrectPlayersToPlayersNotReady)
                .as("3 of the 3 players should not be ready");
    }
}