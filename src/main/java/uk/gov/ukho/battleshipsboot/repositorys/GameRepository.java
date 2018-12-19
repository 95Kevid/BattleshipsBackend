package uk.gov.ukho.battleshipsboot.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.ukho.battleshipsboot.model.game.Game;


public interface GameRepository extends CrudRepository<Game, Integer> {

}
