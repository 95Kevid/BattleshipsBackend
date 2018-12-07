package uk.gov.ukho.battleshipsboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.Player;

@EnableJpaRepositories
@SpringBootApplication
public class BattleshipsbootApplication {



    public static void main(String[] args) {

        SpringApplication.run(BattleshipsbootApplication.class, args);



    }




}
