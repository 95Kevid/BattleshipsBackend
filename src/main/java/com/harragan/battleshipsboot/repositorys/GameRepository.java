package com.harragan.battleshipsboot.repositorys;

import org.springframework.data.repository.CrudRepository;
import com.harragan.battleshipsboot.model.game.Game;


public interface GameRepository extends CrudRepository<Game, Integer> {

}
