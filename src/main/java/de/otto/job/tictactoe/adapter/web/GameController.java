package de.otto.job.tictactoe.adapter.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.otto.job.tictactoe.domain.model.Game;
import de.otto.job.tictactoe.domain.model.GameId;
import de.otto.job.tictactoe.domain.port.GameRepository;
import de.otto.job.tictactoe.domain.port.GamingUseCase;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

  private final GamingUseCase gamingUseCase;
  private final GameRepository gameRepository;
  private final ObjectMapper objectMapper;

  public GameController(GamingUseCase gamingUseCase, GameRepository gameRepository,
      ObjectMapper objectMapper) {
    this.gamingUseCase = gamingUseCase;
    this.gameRepository = gameRepository;
    this.objectMapper = objectMapper;
  }

  @PutMapping(path = "/game/start")
  public ResponseEntity<?> startGame() {
    GameId gameId = gamingUseCase.createNewGame();
    return ResponseEntity.ok(new StartGameResponseDto(gameId.value()));
  }

  @GetMapping(path = "/game")
  public ResponseEntity<?> getGameState(
      @RequestParam("gameId") String gameIdParam
  ) {
    Optional<Game> gameOpt = gameRepository.findByGameId(
        new GameId(gameIdParam));
    if (gameOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    try {
      return ResponseEntity.ok("Game: " + objectMapper.writeValueAsString(gameOpt.get()));
    } catch (JsonProcessingException e) {
      return ResponseEntity.internalServerError()
          .body("Error: " + e.getMessage());
    }
  }

  @PostMapping(path = "/game")
  public ResponseEntity<?> performMove(
      @RequestParam("gameId") String gameIdParam,
      @RequestBody String payload
  ) {
    Optional<Game> gameOpt = gameRepository.findByGameId(
        new GameId(gameIdParam));
    if (gameOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    MakeMoveRequestDto requestDto;
    try {
      requestDto = objectMapper.readValue(payload, MakeMoveRequestDto.class);
    } catch (JsonProcessingException e) {
      return ResponseEntity.badRequest()
          .body("Error: " + e.getMessage());
    }

    gamingUseCase.performMove(gameOpt.get().gameId(),
        requestDto.player(),
        requestDto.x(), requestDto.y());
    return ResponseEntity.ok("game updated");
  }

  public record StartGameResponseDto(String gameId) {

  }

  public record MakeMoveRequestDto(
      int player,
      int x,
      int y
  ) {

  }
}
