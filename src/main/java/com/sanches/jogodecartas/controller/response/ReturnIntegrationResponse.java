package com.sanches.jogodecartas.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanches.jogodecartas.controller.request.CardsRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReturnIntegrationResponse {

    private boolean success;

    @JsonProperty("deck_id")
    private String deckId;

    private Integer remaining;

    private boolean shuffled;

    private List<CardsRequest> cards;

}
