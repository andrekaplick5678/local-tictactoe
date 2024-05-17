package de.otto.job.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.otto.job.tictactoe.application.service.CreateGameService;
import de.otto.job.tictactoe.domain.Game;
import de.otto.job.tictactoe.domain.PlayerName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateGameServiceTest {

  private CreateGameService createGameService;

  @BeforeEach
  void setup() {
    createGameService = new CreateGameService();
  }

  @Test
  void shouldCreateGame() {
    // given: two players
    PlayerName playerName1 = new PlayerName("player1");
    PlayerName playerName2 = new PlayerName("player2");

    // when: method is called
    Game game = createGameService.startGame(playerName1, playerName2);

    // then: game has been created
    assertNotNull(game);
    assertThat(game.playerName1()).isEqualTo(playerName1);
    assertThat(game.playerName2()).isEqualTo(playerName2);
    assertThat(game.gameId()).isNotNull();
    assertThat(game.activePlayer()).isEqualTo(playerName1);
    assertThat(game.gameField()).isNotNull();
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        assertThat(game.gameField().value()[x][y]).isNull();
      }
    }
  }

}