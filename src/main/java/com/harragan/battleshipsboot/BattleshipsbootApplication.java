package com.harragan.battleshipsboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class BattleshipsbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(BattleshipsbootApplication.class, args);
  }
}
