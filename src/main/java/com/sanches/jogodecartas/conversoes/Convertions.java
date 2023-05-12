package com.sanches.jogodecartas.conversoes;

import com.sanches.jogodecartas.controller.request.CardsRequest;
import com.sanches.jogodecartas.controller.request.HandRequest;
import com.sanches.jogodecartas.controller.response.GameResponse;
import com.sanches.jogodecartas.controller.response.ReturnIntegrationResponse;
import com.sanches.jogodecartas.controller.response.WinnerGameResponse;
import com.sanches.jogodecartas.entity.EntityInitializerGame;
import com.sanches.jogodecartas.entity.EntityWinnerGame;
import com.sanches.jogodecartas.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class Convertions {

    public static EntityInitializerGame convertReturnIntegrationToEntityInitializerGame(ReturnIntegrationResponse returnIntegration) {
        EntityInitializerGame initializerGame = EntityInitializerGame.builder().build();
        initializerGame.setDeckId(returnIntegration.getDeckId());
        initializerGame.setRemaining(returnIntegration.getRemaining());
        initializerGame.setDateRegister(ConverterUtil.nowTime());
        return initializerGame;
    }

    public static GameResponse convertEntityInitializerGameToGameResponse(EntityInitializerGame initializerGameSave) {
        GameResponse gameResponse = GameResponse.builder().build();
        gameResponse.setIdGame(initializerGameSave.getIdGame());
        gameResponse.setDeckId(initializerGameSave.getDeckId());
        gameResponse.setRemaining(initializerGameSave.getRemaining());
        gameResponse.setDateRegister(initializerGameSave.getDateRegister());
        return gameResponse;
    }

    public WinnerGameResponse convertEntityToResponseWinnerGame(EntityWinnerGame winnerGame) {
        WinnerGameResponse winnerGameResponse = WinnerGameResponse.builder().build();
        winnerGameResponse.setIdRound(winnerGame.getIdRound());
        winnerGameResponse.setScoreWinner(winnerGame.getScoreWinner());
        winnerGameResponse.setRoundWinner(winnerGame.getRoundWinner());
        winnerGameResponse.setIdGame(winnerGame.getInitializerGame().getIdGame());
        winnerGameResponse.setDateRegister(ConverterUtil.nowTime());

        return winnerGameResponse;
    }

    public static void hands(List<CardsRequest> cards, List<HandRequest> hands) {
        hands.add(new HandRequest("Jessica", cards.subList(0, 5)));
        hands.add(new HandRequest("Valentina", cards.subList(5, 10)));
        hands.add(new HandRequest("Cristofer", cards.subList(10, 15)));
        hands.add(new HandRequest("Marcos", cards.subList(15, 20)));
    }
}