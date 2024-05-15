package de.otto.job.tictactoe.domain.port;

import de.otto.job.tictactoe.domain.model.Game;
import de.otto.job.tictactoe.domain.model.GameId;
import java.util.Optional;

public interface GameRepository {

  void save(Game game);

  Optional<Game> findByGameId(GameId gameId);

}
