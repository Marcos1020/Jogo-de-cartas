package com.sanches.jogodecartas.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WinnerGameResponse {

    private Long idRodada;

    private String vencedorDaRodada;

    private Long pontuacaoVencedor;

    private Date dateRegister;

}