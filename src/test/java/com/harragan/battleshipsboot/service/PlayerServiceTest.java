package com.harragan.battleshipsboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

  @InjectMocks private PlayerService playerService;

  @Mock private PlayerRepository playerRepository;

  @Before
  public void initTest() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void playersCanBeAssignedAGameArenaWithAnId() {
    final GameArena gameArena = new GameArena();
    final Player player = new Player();
    player.setId(1);
    playerService.setArenaToPlayer(gameArena, player);
    assertThat(player.getGameArena()).isEqualTo(gameArena);
  }

  @Test
  public void whenAPlayerIsInstantiatedAPlayerIdIsReturned() {
    final Player playerJo = new Player("Jo White");
    when(playerRepository.save(any(Player.class))).thenReturn(playerJo);
    final Player result = playerService.createPlayer("Jo White", playerRepository);
    assertThat(playerJo).isEqualTo(result);
  }

  @Test
  public void whenProvidedWithAPlayerIdThePlayerIsFlaggedAsStarted() {
    final Player playerJo = new Player("Jo White");
    playerJo.setId(1);

    playerJo.setGameArena(new GameArena());
    playerJo.getGameArena().setAllShipsPlaced(true);
    when(playerRepository.findById(playerJo.getId())).thenReturn(Optional.of(playerJo));
    playerService.setPlayerIsReady(1, playerRepository);
    assertThat(playerJo.isReadyToStartGame());
  }


}
