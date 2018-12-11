package uk.gov.ukho.battleshipsboot.repositorys;

import org.springframework.data.repository.CrudRepository;
import uk.gov.ukho.battleshipsboot.model.game.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
