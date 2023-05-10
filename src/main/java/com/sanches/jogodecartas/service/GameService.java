package com.sanches.jogodecartas.service;

import com.sanches.jogodecartas.calculatewinner.CalculateAndReturnWinner;
import com.sanches.jogodecartas.controller.request.CardsRequest;
import com.sanches.jogodecartas.controller.request.GameRequest;
import com.sanches.jogodecartas.controller.request.HandRequest;
import com.sanches.jogodecartas.controller.response.*;
import com.sanches.jogodecartas.conversoes.Conversoes;
import com.sanches.jogodecartas.entity.EntityInitializerGame;
import com.sanches.jogodecartas.entity.EntityWinnerGame;
import com.sanches.jogodecartas.exception.BadRequestException;
import com.sanches.jogodecartas.integrationgame.CreateDeckIntegration;
import com.sanches.jogodecartas.integrationgame.InitializerGameIntegration;
import com.sanches.jogodecartas.repository.GameInitializerRepository;
import com.sanches.jogodecartas.repository.GameWinnerRepository;
import com.sanches.jogodecartas.utils.ConverterUtil;
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
        ReturnIntegrationResponse returnIntegration = this.deckIntegration.initializerNewDeck(deckCount);

        EntityInitializerGame initializerGame = conversoes.convertReturnIntegrationToEntityInitializerGame(returnIntegration);
        EntityInitializerGame initializerGameSave = this.initializerRepository.save(initializerGame);

        GameResponse gameResponse = conversoes.convertEntityInitializerGameToGameResponse(initializerGameSave);
        return gameResponse;
    }

    public List<EntityInitializerGame> getAllDecks() {

        return initializerRepository.findAll();
    }

    public WinnerGameResponse gamePlay(GameRequest gameRequest) throws BadRequestException {

        ReturnIntegrationResponse returnIntegration = this.gameIntegration.initializerGame(gameRequest.getDeckId(), gameRequest.getCount());
        List<CardsRequest> cards = returnIntegration.getCards();

        if (gameRequest.getCount() > returnIntegration.getRemaining()){
            log.info(GameConstants.ERROR_DRAW_DECK);
            throw new BadRequestException(GameConstants.ERROR_DRAW_DECK);
        }

        List<HandRequest> hands = new ArrayList<>();
        hands.add(new HandRequest("Jessica", cards.subList(0, 5)));
        hands.add(new HandRequest("Valentina", cards.subList(5, 10)));
        hands.add(new HandRequest("Cristofer", cards.subList(10, 15)));
        hands.add(new HandRequest("Marcos", cards.subList(15, 20)));

        EntityInitializerGame initializerGame = this.initializerRepository.findByDeckId(gameRequest.getDeckId());

        EntityWinnerGame winnerGameSave = EntityWinnerGame.builder().build();
        Integer maxScore = 0;
        EntityWinnerGame winnerGame = EntityWinnerGame.builder().build();
        List<HandRequest> winners = new ArrayList<>();
        for (HandRequest hand : hands) {
            maxScore = calculate.getMaxScore(maxScore, winners, hand);
            winnerGame.setScoreWinner(maxScore);
            winnerGame.setRoundWinner(hand.getPlayerName());
            winnerGame.setDateRegister(ConverterUtil.nowTime());
            winnerGame.setInitializerGame(initializerGame);
        }

        if (winnerGame.getScoreWinner().equals(maxScore)){
            winnerGameSave = this.gameRepository.save(winnerGame);
        }
        return  conversoes.convertEntityToResponseWinnerGame(winnerGameSave);
    }
}