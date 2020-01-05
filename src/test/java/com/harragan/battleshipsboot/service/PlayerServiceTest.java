package com.harragan.battleshipsboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

  @InjectMocks
  private PlayerService playerService;

  @Mock
  private PlayerRepository playerRepository;

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

  @Test
  public void whenAListOfPlayersIsSuppliedThenAMapOfThosePlayersToShotPositionsIsReturned(){
    Player player1 = new Player("Kevin");
    Player player2 = new Player("Helga");
    Player player3 = new Player("Burnard");

    BoardPosition positionA1 = BoardPositionFactory.createBoardPosition('A', 1);
    BoardPosition positionB7 = BoardPositionFactory.createBoardPosition('B', 7);
    BoardPosition positionJ7 = BoardPositionFactory.createBoardPosition('J', 7);

    positionA1.setHit(true);
    positionB7.setHit(true);
    positionJ7.setHit(true);
    GameArena gameArena1 = new GameArena();
    GameArena gameArena2 = new GameArena();
    GameArena gameArena3 = new GameArena();
    gameArena1.addShotPosition(positionA1);
    gameArena1.addShotPosition(positionB7);
    gameArena2.addShotPosition(positionA1);
    gameArena2.addShotPosition(positionJ7);
    gameArena3.addShotPosition(positionB7);
    player1.setGameArena(gameArena1);
    player2.setGameArena(gameArena2);
    player3.setGameArena(gameArena3);

    Set<Player> players = new HashSet<>();
    players.add(player1);
    players.add(player2);
    players.add(player3);

    Set<BoardPosition> player1ShotPositions = new HashSet<>();
    Set<BoardPosition> player2ShotPositions = new HashSet<>();
    Set<BoardPosition> player3ShotPositions = new HashSet<>();
    player1ShotPositions.add(positionA1);
    player1ShotPositions.add(positionB7);
    player2ShotPositions.add(positionA1);
    player2ShotPositions.add(positionJ7);
    player3ShotPositions.add(positionB7);
    Map<Player, Set<BoardPosition>> expectedResult = new HashMap<>();
    expectedResult.put(player1, player1ShotPositions);
    expectedResult.put(player2, player2ShotPositions);
    expectedResult.put(player3, player3ShotPositions);


    assertThat(expectedResult.equals(playerService.getPlayersToShotPositions(players))).isTrue();
  }
}
