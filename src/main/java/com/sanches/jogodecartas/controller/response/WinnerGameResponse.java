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

    private Long idRound;

    private Long idGame;

    private String roundWinner;

    private Integer scoreWinner;

    private Date dateRegister;

}