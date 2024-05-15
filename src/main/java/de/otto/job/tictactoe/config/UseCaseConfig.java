package de.otto.job.tictactoe.config;

import de.otto.job.tictactoe.domain.port.GameRepository;
import de.otto.job.tictactoe.domain.port.GamingUseCase;
import de.otto.job.tictactoe.domain.service.GamingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class UseCaseConfig {

  @Bean
  public GamingUseCase gamingUseCase(GameRepository gameRepository) {
    return new GamingService(gameRepository);
  }
}
