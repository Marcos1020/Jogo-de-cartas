package com.sanches.jogodecartas.controller;

import com.sanches.jogodecartas.controller.request.GameRequest;
import com.sanches.jogodecartas.controller.response.GameResponse;
import com.sanches.jogodecartas.entity.EntityInitializerGame;
import com.sanches.jogodecartas.entity.EntityWinnerGame;
import com.sanches.jogodecartas.exception.BadRequestException;
import com.sanches.jogodecartas.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/jogo-de-cartas/")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<?> startingTheGame(
            @RequestParam(
                    name = "deck_count",
                    required = true,
                    value = "deck_count") final Integer deckCount) throws BadRequestException {
        GameResponse gameResponse = this.gameService.initializerGame(deckCount);
        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
    }

    @GetMapping("list/all")
    public List<EntityInitializerGame> getAll() {
        return gameService.getAll();
    }

    @PostMapping("initializer/game-play")
    public ResponseEntity<?> initializerGame(
           @Valid @RequestBody GameRequest gameRequest) throws BadRequestException {
        GameResponse gameResponse = this.gameService.gamePlay(gameRequest);
        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
    }
}
