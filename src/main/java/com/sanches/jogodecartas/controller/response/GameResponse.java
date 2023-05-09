package com.sanches.jogodecartas.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GameResponse {

    private Long idGame;

    private Integer remaining;

    private String deckId;

    private Date dateRegister;

    private WinnerGameResponse winnerGameResponse;

    private List<Cards> cardsList;
}
