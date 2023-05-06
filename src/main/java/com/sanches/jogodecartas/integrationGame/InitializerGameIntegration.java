package com.sanches.jogodecartas.integrationGame;

import com.sanches.jogodecartas.controller.response.ReturnIntegration;
import com.sanches.jogodecartas.exception.BadRequestException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "draw-card-initialazer-game", url = "${feign.client.config.draw-card-initialazer-game.url}")
public interface InitializerGameIntegration {

    @GetMapping(value = "/{deck_id}/draw/", consumes = MediaType.APPLICATION_JSON_VALUE)
    ReturnIntegration initializerGame(
            @PathVariable("deck_id")final String deckId,
            @RequestParam("count_draw") final Integer countDraw)throws BadRequestException;
}