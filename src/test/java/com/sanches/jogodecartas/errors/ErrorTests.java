package com.sanches.jogodecartas.errors;

import com.sanches.jogodecartas.exception.BadRequestException;
import com.sanches.jogodecartas.integrationgame.InitializerGameIntegration;
import com.sanches.jogodecartas.service.GameService;
import com.sanches.jogodecartas.utils.GameConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ErrorTests {

    @InjectMocks
    private GameService gameService;

    private InitializerGameIntegration gameIntegration;

    @Autowired
    public ErrorTests(InitializerGameIntegration gameIntegration) {
        this.gameIntegration = gameIntegration;
    }

    @Test
    public void shouldReturnErrorWhenTryingToGenerateANewDeck() {

        Integer deckCount = 0;
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            gameService.initializerGame(deckCount);
        });

        String expectedMessage = GameConstants.ERROR_INITIALIZER_GAME;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void mustTestANullReturn() {

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            gameService.initializerGame(null);
        });

        String expectedMessage = GameConstants.ERROR_NULL_DECK_COUNT;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}