package com.sanches.jogodecartas.errors;

import com.sanches.jogodecartas.exception.BadRequestException;
import com.sanches.jogodecartas.service.GameService;
import com.sanches.jogodecartas.utils.GameConstants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorTests {

    @InjectMocks
    private GameService gameService;

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
}
