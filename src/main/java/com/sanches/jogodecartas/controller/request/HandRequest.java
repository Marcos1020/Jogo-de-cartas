package com.sanches.jogodecartas.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HandRequest {

    private String playerName;

    private List<CardsRequest> cards;

}