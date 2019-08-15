package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameStatusResponse;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.game.PlayerInGameInfo;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;
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
  private final PlayerService playerService;

  public GameStatusFacade(final GameService gameService, final PlayerService playerService) {
    this.gameService = gameService;
    this.playerService = playerService;
  }

  public GameStatusResponse getGameStatus(final int gameId) {
    final Game game = gameService.getGame(gameId);
    final Set<Player> players = new HashSet<>(game.getPlayers());
    final Map<Player, Set<BoardPosition>> playersToHitPositions = playerService.getPlayersToShotPositions(players);
    final Map<Player, Set<Ship>> playersToSunkShips = players.stream()
        .collect(Collectors.toMap(Function.identity(), this::extractSunkShips));
    final Set<PlayerInGameInfo> playerInGameInfos = game.getPlayers().stream()
            .map(player -> createPlayerInGameInfo(playersToHitPositions, playersToSunkShips, player))
            .collect(Collectors.toSet());

    return new GameStatusResponse(gameService.checkForTurn(gameId).getId(), playerInGameInfos);
  }

  @NotNull
  private PlayerInGameInfo createPlayerInGameInfo(Map<Player, Set<BoardPosition>> playersToHitPositions,
                                                  Map<Player, Set<Ship>> playersToSunkShips, Player player) {
    return new PlayerInGameInfo(player.getId(), player.getName(),
    playersToHitPositions.get(player), playersToSunkShips.get(player), player.isWinner());
  }

  @NotNull
  private HashSet<Ship> extractSunkShips(final Player player) {
    return new HashSet<>(player.getGameArena().getSunkShips());
  }
}
