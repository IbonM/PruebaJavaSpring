package com.example.prueba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(Repositorio repository) {

    return args -> {
    	log.info("Preloading " + repository.save(new Coche("Seat", "1", "1234ABC")));
    	log.info("Preloading " + repository.save(new Coche("Renault", "3", "9876ZYX")));
    };
  }
}