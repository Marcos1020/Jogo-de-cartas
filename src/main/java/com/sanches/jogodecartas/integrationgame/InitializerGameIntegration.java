package com.sanches.jogodecartas.integrationgame;

import com.sanches.jogodecartas.controller.response.ReturnIntegrationResponse;
import com.sanches.jogodecartas.exception.BadRequestException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "draw-card-initialazer-game", url = "${feign.client.config.draw-card-initialazer-game.url}")
public interface InitializerGameIntegration {

    @GetMapping(value = "/{deck_id}/draw/")
    ReturnIntegrationResponse initializerGame(
            @PathVariable("deck_id")final String deckId,
            @RequestParam("count") final Integer count)throws BadRequestException;
}