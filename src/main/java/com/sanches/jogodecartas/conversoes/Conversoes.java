package com.sanches.jogodecartas.conversoes;

import com.sanches.jogodecartas.controller.response.GameResponse;
import com.sanches.jogodecartas.controller.response.ReturnIntegration;
import com.sanches.jogodecartas.entity.EntityInitializerGame;
import com.sanches.jogodecartas.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Conversoes {

    public static EntityInitializerGame convertReturnIntegrationToEntityInitializerGame(ReturnIntegration returnIntegration) {
        EntityInitializerGame initializerGame = EntityInitializerGame.builder().build();
        initializerGame.setDeck_id(returnIntegration.getDeckId());
        initializerGame.setRemaining(returnIntegration.getRemaining());
        initializerGame.setDateRegister(ConverterUtil.nowTime());
        return initializerGame;
    }

    public static GameResponse convertEntityInitializerGameToGameResponse(EntityInitializerGame initializerGameSave) {
        GameResponse gameResponse = GameResponse.builder().build();
        gameResponse.setIdGame(initializerGameSave.getIdGame());
        gameResponse.setDeckId(initializerGameSave.getDeck_id());
        gameResponse.setRemaining(initializerGameSave.getRemaining());
        gameResponse.setDateRegister(initializerGameSave.getDateRegister());
        return gameResponse;
    }
}
