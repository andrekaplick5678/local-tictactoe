# Tic Tac Toe game server

## How to start locally

```bash
./gradlew clean bootRun
```


## How to access

- open http://localhost:8080/actuator/info

## REST API

```bash
## start a new game
curl -X PUT "http://localhost:8080/game/start"
```

Response:
```json
{
  "gameId": "9fe501b1-420e-449a-b137-47117e435ece"
}
```

```bash
## show game state
curl -s -X GET "http://localhost:8080/game?gameId=9fe501b1-420e-449a-b137-47117e435ece"
```

```bash
## make move
curl -X POST \
  -H 'Content-Type: application/json' \
  -d '{"player": 1, "x": 3, "y": 2}' \
  "http://localhost:8080/game?gameId=9fe501b1-420e-449a-b137-47117e435ece"
```
