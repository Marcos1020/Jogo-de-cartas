package com.sanches.jogodecartas.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GameRequest {

    @JsonProperty("deck_id")
    private String deckId;

    @JsonProperty("count")
    private Integer countDraw;
}
