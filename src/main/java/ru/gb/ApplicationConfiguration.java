package ru.gb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

// java-based configuration
@Configuration

public class ApplicationConfiguration {
  private JdbcTemplate jdbcTemplate;
@Bean
  UserRepository myUserRepository() {
    return new UserRepository(jdbcTemplate);
  }

}
