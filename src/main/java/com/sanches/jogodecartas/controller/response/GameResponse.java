package com.sanches.jogodecartas.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GameResponse {

    private Long idRodada;
    private String vencedorDaRodada;
    private Long pontuacaoVencedor;
    private String deckId;
}
