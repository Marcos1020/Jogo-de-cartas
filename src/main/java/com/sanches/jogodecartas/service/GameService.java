package com.sanches.jogodecartas.service;

import com.sanches.jogodecartas.controller.request.GameRequest;
import com.sanches.jogodecartas.controller.response.GameResponse;
import com.sanches.jogodecartas.controller.response.ReturnIntegration;
import com.sanches.jogodecartas.conversoes.Conversoes;
import com.sanches.jogodecartas.entity.EntityInitializerGame;
import com.sanches.jogodecartas.exception.BadRequestException;
import com.sanches.jogodecartas.integrationGame.CreateDeckIntegration;
import com.sanches.jogodecartas.integrationGame.InitializerGameIntegration;
import com.sanches.jogodecartas.repository.GameInitializerRepository;
import com.sanches.jogodecartas.repository.GameWinnerRepository;
import com.sanches.jogodecartas.utils.GameConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Component
public class GameService {

    private final Conversoes conversoes;
    private GameInitializerRepository initializerRepository;
    private GameWinnerRepository gameRepository;
    private CreateDeckIntegration deckIntegration;
    private InitializerGameIntegration gameIntegration;

    @Autowired
    public GameService(Conversoes conversoes,
                       GameInitializerRepository initializerRepository,
                       GameWinnerRepository gameRepository,
                       CreateDeckIntegration deckIntegration,
                       InitializerGameIntegration gameIntegration){
        this.conversoes = conversoes;
        this.initializerRepository = initializerRepository;
        this.gameRepository = gameRepository;
        this.deckIntegration = deckIntegration;
        this.gameIntegration = gameIntegration;
    }

    public GameResponse initializerGame(final Integer deckCount)throws BadRequestException{

        Integer deckError = 0;
        if (deckCount == deckError){
            log.info(GameConstants.ERROR_INITIALIZER_GAME);
            throw new BadRequestException(GameConstants.ERROR_INITIALIZER_GAME);
        }
        ReturnIntegration returnIntegration = this.deckIntegration.initializerNewDeck(deckCount);

        EntityInitializerGame initializerGame = conversoes.convertReturnIntegrationToEntityInitializerGame(returnIntegration);
        EntityInitializerGame initializerGameSave = this.initializerRepository.save(initializerGame);

        GameResponse gameResponse = conversoes.convertEntityInitializerGameToGameResponse(initializerGameSave);
        return gameResponse;
    }

    public List<EntityInitializerGame> getAll() {

        return initializerRepository.findAll();
    }

    public GameResponse gamePlay(GameRequest gameRequest)throws BadRequestException{

        ReturnIntegration returnIntegration = this.gameIntegration.initializerGame(gameRequest.getDeckId(), gameRequest.getCountDraw());
        GameResponse gameResponse = GameResponse.builder().build();
        gameResponse.setRemaining(returnIntegration.getRemaining());
        gameResponse.setDeckId(returnIntegration.getDeckId());

        return gameResponse;
    }
}
