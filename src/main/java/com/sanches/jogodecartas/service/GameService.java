package com.sanches.jogodecartas.service;

import com.sanches.jogodecartas.controller.response.GameResponse;
import com.sanches.jogodecartas.controller.response.ReturnIntegration;
import com.sanches.jogodecartas.exception.BadRequestException;
import com.sanches.jogodecartas.integrationGame.CreateDeckIntegration;
import com.sanches.jogodecartas.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Component
public class GameService {

    private GameRepository gameRepository;

    private CreateDeckIntegration deckIntegration;

    @Autowired
    public GameService(GameRepository gameRepository, CreateDeckIntegration deckIntegration){
        this.gameRepository = gameRepository;
        this.deckIntegration = deckIntegration;
    }

    public GameResponse initializerGame(final Integer deckCount)throws BadRequestException{

        //Regra numero 1 Salavar os dados retornados
        //criar um getAll que busca todos os IdsSalvos
        ReturnIntegration returnIntegration = this.deckIntegration.initializerGame(deckCount);

        //testando o retorno da integração
        GameResponse gameResponse = GameResponse.builder().build();
        gameResponse.setDeckId(returnIntegration.getDeckId());
        log.info(returnIntegration.getDeckId());

        return gameResponse;
    }
}
