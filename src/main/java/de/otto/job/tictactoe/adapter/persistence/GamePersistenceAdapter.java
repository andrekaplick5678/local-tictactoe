package de.otto.job.tictactoe.adapter.persistence;

import de.otto.job.tictactoe.domain.model.Game;
import de.otto.job.tictactoe.domain.model.GameId;
import de.otto.job.tictactoe.domain.port.GameRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class GamePersistenceAdapter implements GameRepository {

  private final Map<GameId, Game> games = new HashMap<>();

  @Override
  public void save(Game game) {
    games.put(game.gameId(), game);
  }

  @Override
  public Optional<Game> findByGameId(GameId gameId) {
    return Optional.ofNullable(games.get(gameId));
  }
}
