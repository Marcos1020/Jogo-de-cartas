package com.sanches.jogodecartas.service;

import com.sanches.jogodecartas.calculatewinner.CalculateAndReturnWinner;
import com.sanches.jogodecartas.controller.request.GameRequest;
import com.sanches.jogodecartas.controller.response.*;
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
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
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
    private CalculateAndReturnWinner calculate;

    @Autowired
    public GameService(Conversoes conversoes,
                       GameInitializerRepository initializerRepository,
                       GameWinnerRepository gameRepository,
                       CreateDeckIntegration deckIntegration,
                       InitializerGameIntegration gameIntegration, CalculateAndReturnWinner calculate){
        this.conversoes = conversoes;
        this.initializerRepository = initializerRepository;
        this.gameRepository = gameRepository;
        this.deckIntegration = deckIntegration;
        this.gameIntegration = gameIntegration;
        this.calculate = calculate;
    }

    public GameResponse initializerGame(final Integer deckCount)throws BadRequestException{

        Integer deckError = 0;
        if (deckCount == deckError){
            log.info(GameConstants.ERROR_INITIALIZER_GAME);
            throw new BadRequestException(GameConstants.ERROR_INITIALIZER_GAME);

        } else if (ObjectUtils.isEmpty(deckCount)) {
            log.info(GameConstants.ERROR_NULL_DECK_COUNT);
            throw new BadRequestException(GameConstants.ERROR_NULL_DECK_COUNT);
        }
        ReturnIntegration returnIntegration = this.deckIntegration.initializerNewDeck(deckCount);

        EntityInitializerGame initializerGame = conversoes.convertReturnIntegrationToEntityInitializerGame(returnIntegration);
        EntityInitializerGame initializerGameSave = this.initializerRepository.save(initializerGame);

        GameResponse gameResponse = conversoes.convertEntityInitializerGameToGameResponse(initializerGameSave);
        return gameResponse;
    }

    public List<EntityInitializerGame> getAllDecks() {

        return initializerRepository.findAll();
    }

    public GameResponse gamePlay(GameRequest gameRequest) throws BadRequestException {

        ReturnIntegration returnIntegration = this.gameIntegration.initializerGame(gameRequest.getDeckId(), gameRequest.getCount());
        List<Cards> cards = returnIntegration.getCards();

        if (gameRequest.getCount() < returnIntegration.getRemaining()){
            log.info("Erro, Numero de cartas menor que o solicitado");
            throw new BadRequestException("Erro, Numero de cartas menor que o solicitado");

        }

        List<Hand> hands = new ArrayList<>();
        hands.add(new Hand("Jessica", cards.subList(0, 5)));
        hands.add(new Hand("Valentina", cards.subList(5, 10)));
        hands.add(new Hand("Cristofer", cards.subList(10, 15)));
        hands.add(new Hand("Marcos", cards.subList(15, 20)));

        int maxScore = 0;
        List<Hand> winners = new ArrayList<>();
        for (Hand hand : hands) {
            maxScore = calculate.getMaxScore(maxScore, winners, hand);
        }

        GameResponse gameResponse = GameResponse.builder().build();
        gameResponse.getWinnerGameResponse().getPontuacaoVencedor();
        gameResponse.getWinnerGameResponse().getIdRodada();
        gameResponse.getWinnerGameResponse().getVencedorDaRodada();

        return gameResponse;
    }
}