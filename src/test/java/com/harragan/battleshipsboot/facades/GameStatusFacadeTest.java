package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.GameStatusResponse;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.ShipType;
import com.harragan.battleshipsboot.service.BoardPositionFactory;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class GameStatusFacadeTest {

  @Mock
  private GameArenaService gameArenaService;
  @Mock
  private GameService gameService;
  private GameStatusFacade gameStatusFacade;
  @Mock
  private Game game;
  @Mock
  private PlayerService playerService;
  private Player player1;
  private Player player2;
  private GameArena gameArena1;
  private GameArena gameArena2;
  private LinkedList<Player> players;
  private Set<GameArena> gameArenas;
  private BoardPosition boardPositionA1;
  private BoardPosition boardPositionA2;
  private BoardPosition boardPositionC4;
  private BoardPosition boardPositionC5;
  private BoardPosition boardPositionC6;
  private Set<BoardPosition> hitBoardPositions;
  private Ship destroyer;
  private Ship submarine;


  @Before
  public void initTest() {
    MockitoAnnotations.initMocks(this);
    gameStatusFacade = new GameStatusFacade(
        gameService, gameArenaService, playerService);
    gameArena1 = new GameArena();
    gameArena2 = new GameArena();
    boardPositionA1 = BoardPositionFactory.createBoardPosition('A', 1);
    boardPositionA2 = BoardPositionFactory.createBoardPosition('A', 2);
    boardPositionC4 = BoardPositionFactory.createBoardPosition('C', 4);
    boardPositionC5 = BoardPositionFactory.createBoardPosition('C', 5);
    boardPositionC6 = BoardPositionFactory.createBoardPosition('C', 6);
    player1 = new Player("Kevin");
    player2 = new Player("David");
    player1.setGameArena(gameArena1);
    player2.setGameArena(gameArena2);
    players = new LinkedList<>(Arrays.asList(player1, player2));
    gameArenas = new HashSet<>();
    gameArenas.add(gameArena1);
    gameArenas.add(gameArena2);
    hitBoardPositions = new HashSet<>();
    hitBoardPositions.add(boardPositionA1);
    List<BoardPosition> occupiedPositionsOfDestroyer = new ArrayList<>();
    occupiedPositionsOfDestroyer.add(boardPositionA1);
    occupiedPositionsOfDestroyer.add(boardPositionA2);
    List<BoardPosition>occupiedPositionsOfSubmarine = new ArrayList<>();
    occupiedPositionsOfSubmarine.add(boardPositionC4);
    occupiedPositionsOfSubmarine.add(boardPositionC5);
    occupiedPositionsOfSubmarine.add(boardPositionC6);
    destroyer = new Ship(1, Orientation.HORIZONTAL, boardPositionA1, occupiedPositionsOfDestroyer, true, ShipType.DESTROYER);
    submarine = new Ship(1, Orientation.VERTICAL, boardPositionC4, occupiedPositionsOfSubmarine, true, ShipType.SUBMARINE);
    gameArena1.addSunkenShip(destroyer);
    gameArena2.addSunkenShip(submarine);
  }

  @Test
  public void whenAGameIdIsSuppliedThenAGameStatusResponseIsReturnedContainingThePlayersIdWhosTurnItIs() {
    setupMocks();
    GameStatusResponse gameStatusResponse = gameStatusFacade.getGameStatus(1);
    assertThat(gameStatusResponse.getPlayersTurnId()).isEqualTo(1);
  }

  @Test
  public void whenAGameIdIsSuppliedThenAGameStatusResponseIsReturnedContainingTheHitBoardPositions() {
    setupMocks();
    GameStatusResponse gameStatusResponse = gameStatusFacade.getGameStatus(1);
    assertThat(gameStatusResponse.getHitBoardPositions().contains(boardPositionA1)
        && gameStatusResponse.getHitBoardPositions().contains(boardPositionC4));
  }

  @Test
  public void whenAGameIdIsSuppliedThenAGameStatusResponseIsReturnedContainingAMapOfAnyPlayersThatHaveSunkShipsAndTheShipsThatAreSunk() {
    setupMocks();
    Map<Player, Set<Ship>> playersToSunkShips = createMapOfPlayersToSunkenShips();
    GameStatusResponse gameStatusResponse = gameStatusFacade.getGameStatus(1);
    assertThat(gameStatusResponse.getPlayersToSunkShips()).isEqualTo(playersToSunkShips);
  }

  private void setupMocks() {
    when(gameService.checkForTurn(1)).thenReturn(1);
    when(gameArenaService.getHitPositions(gameArenas)).thenReturn(hitBoardPositions);
    when(gameService.getGame(1)).thenReturn(game);
    when(game.getPlayers()).thenReturn(players);
  }

  private Map<Player, Set<Ship>> createMapOfPlayersToSunkenShips() {
    Map<Player, Set<Ship>> playersToSunkShips = new HashMap<>();
    Set<Ship> player1SunkShips = new HashSet<>();
    player1SunkShips.add(destroyer);
    Set<Ship> player2SunkShips = new HashSet<>();
    player2SunkShips.add(submarine);
    playersToSunkShips.put(player1, player1SunkShips);
    playersToSunkShips.put(player2, player2SunkShips);
    return playersToSunkShips;
  }
}
