package com.sanches.jogodecartas.integrationgame;

import com.sanches.jogodecartas.controller.response.ReturnIntegrationResponse;
import com.sanches.jogodecartas.exception.BadRequestException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "create-deck-game", url = "${feign.client.config.create-deck-game.url}")
public interface CreateDeckIntegration {

    @GetMapping(value = "/new/shuffle/", consumes = MediaType.APPLICATION_JSON_VALUE)
    ReturnIntegrationResponse initializerNewDeck(
            @RequestParam("deck_count")final Integer deckCount)throws BadRequestException;
}
