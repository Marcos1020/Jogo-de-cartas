package com.sanches.jogodecartas.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReturnIntegration {

    private boolean success;

    @JsonProperty("deck_id")
    private String deckId;

    private Integer remaining;

    private boolean shuffled;

    private String value;

    private List<Cards> cards;

}
