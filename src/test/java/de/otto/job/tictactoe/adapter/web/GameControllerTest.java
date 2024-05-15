package de.otto.job.tictactoe.adapter.web;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.otto.job.tictactoe.domain.model.Game;
import de.otto.job.tictactoe.domain.model.Game.FieldStone;
import de.otto.job.tictactoe.domain.model.Game.GameState;
import de.otto.job.tictactoe.domain.model.GameId;
import de.otto.job.tictactoe.domain.port.GameRepository;
import de.otto.job.tictactoe.domain.port.GamingUseCase;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = GameController.class)
class GameControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GamingUseCase gamingUseCase;
  @MockBean
  private GameRepository gameRepository;

  @Test
  void shouldStartNewGame() throws Exception {
    // given: a random gameId
    when(gamingUseCase.createNewGame())
        .thenReturn(new GameId("test-1"));

    // when: UI endpoint is called
    mockMvc
        .perform(put("/game/start"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json("""
            {"gameId": "test-1"}
            """, true));

    // then: use case should have been called
    verify(gamingUseCase).createNewGame();
  }

  @Test
  void shouldPerformValidMove() throws Exception {
    // given: a game
    FieldStone[] gameField = new FieldStone[9];
    Arrays.fill(gameField, FieldStone.EMPTY);
    Game game = new Game(new GameId("test-1"), gameField, GameState.MOVE_PLAYER_ONE);

    // given: game is in repo
    when(gameRepository.findByGameId(game.gameId()))
        .thenReturn(Optional.of(game));

    // when: endpoint is called
    mockMvc
        .perform(post("http://localhost:8080/game?gameId=test-1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"player": 1, "x": 2, "y": 3}
                """))
        .andExpect(status().isOk());

    // then: use case should have been called
    verify(gamingUseCase).performMove(new GameId("test-1"), 1, 2, 3);
  }
}