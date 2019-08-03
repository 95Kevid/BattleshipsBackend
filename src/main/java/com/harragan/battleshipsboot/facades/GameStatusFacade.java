package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameStatusResponse;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GameStatusFacade {

  private final GameService gameService;
  private final GameArenaService gameArenaService;
  private final PlayerService playerService;

  public GameStatusFacade(final GameService gameService, final GameArenaService gameArenaService,
                          final PlayerService playerService) {
    this.gameService = gameService;
    this.gameArenaService = gameArenaService;
    this.playerService = playerService;
  }

  public GameStatusResponse getGameStatus(final int gameId) {
    final Game game = gameService.getGame(gameId);
    final Set<Player> players = new HashSet<>(game.getPlayers());
    final Map<Player, Set<BoardPosition>> playersToHitPositions = playerService.getPlayersToShotPositions(players);
    final Map<Player, Set<Ship>> playersToSunkShips = players.stream()
        .collect(Collectors.toMap(Function.identity(), this::extractSunkShips));
    return new GameStatusResponse(gameService.checkForTurn(gameId).getId(), playersToHitPositions, playersToSunkShips);
  }

  @NotNull
  private HashSet<Ship> extractSunkShips(final Player player) {
    return new HashSet<>(player.getGameArena().getSunkShips());
  }
}
