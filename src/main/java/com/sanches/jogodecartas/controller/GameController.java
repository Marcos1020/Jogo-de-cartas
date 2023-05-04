package com.sanches.jogodecartas.controller;

import com.sanches.jogodecartas.controller.response.GameResponse;
import com.sanches.jogodecartas.exception.BadRequestException;
import com.sanches.jogodecartas.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/jogo-de-cartas/")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<?> startingTheGame(
            @RequestParam(
                    name = "deck_count",
                    required = true,
                    value = "deck_count") final Integer deckCount)throws BadRequestException {
        GameResponse gameResponse = this.gameService.initializerGame(deckCount);
        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
    }
}
