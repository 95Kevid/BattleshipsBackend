package com.harragan.battleshipsboot.repositorys;

import org.springframework.data.repository.CrudRepository;
import com.harragan.battleshipsboot.model.game.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
