package com.harragan.battleshipsboot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerServiceTest {
    private PlayerService playerService;

    @Before
    public void initTest() {
        playerService = new PlayerService(playerRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private PlayerRepository playerRepository;


    @Test
    public void playersCanBeAssignedAGameArenaWithAnId() {
        GameArena gameArena = new GameArena();
        Player player = new Player();
        player.setId(1);
        playerService.setArenaToPlayer(gameArena, player);
        assertThat(player.getGameArena()).isEqualTo(gameArena);
    }

    @Test
    public void givenWhenAPlayerIsInstantiatedAPlayerIdIsReturned() {
        Player playerJO = new Player("Jo White");
        when(playerRepository.save(any(Player.class))).thenReturn(playerJO);
        Player result = playerService.createPlayer("Jo White", playerRepository);
        assertThat(playerJO).isEqualTo(result);
    }

    @Test
    public void givenAPlayeridAPlayerIsMarkedAsStarted() {
        Player playerJo = new Player("Jo White");
        playerJo.setId(1);
        when(playerRepository.findById(playerJo.getId())).thenReturn(Optional.of(playerJo));
        playerService.setPlayerIsReady(1, playerRepository);
        assertThat(playerJo.isReadyToStartGame());
    }
}
