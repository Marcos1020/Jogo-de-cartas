package com.sanches.jogodecartas.successTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanches.jogodecartas.controller.request.GameRequest;
import com.sanches.jogodecartas.entity.EntityInitializerGame;
import com.sanches.jogodecartas.entity.EntityWinnerGame;
import com.sanches.jogodecartas.utils.GameConstants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@AutoConfigureMockMvc
public class IntagratioSuccessTest {

    @Value("${url-which-lists-valid-decks.url}")
    private String generateNewDeck;
    @Value("${url-that-generates-a-new-deck}")
    private String urlListDecks;
    @Value("${url-that-starts-the-game.url}")
    private String gameINitializer;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    IntagratioSuccessTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void mustCreateANewNeckToStartAGame() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(generateNewDeck)
                        .param("deck_count", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        EntityInitializerGame entityReturn = objectMapper.readValue(jsonResponse, EntityInitializerGame.class);

        assertThat(entityReturn).isNotNull();
        assertThat(entityReturn.getRemaining()).isEqualTo(104);
        assertThat(entityReturn.getIdGame()).isEqualTo(1);
    }

    @Test
    public void shouldReturnAListOfAllDecksRegisteredInTheDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(urlListDecks))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<EntityInitializerGame> entityList = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        Optional<Integer> remaining = Optional.of(104);

        assertThat(entityList).isNotNull();
        assertEquals(1, entityList.size());
        assertEquals(remaining, Optional.ofNullable(entityList.get(0).getRemaining()));
    }

    @Test
    public void shouldReturnardGameOkStatus() throws Exception {

        GameRequest gameRequest = GameRequest.builder().build();
        gameRequest.setDeckId("vk9exmabx2is");
        gameRequest.setCount(20);
        String JsonValue = objectMapper.writeValueAsString(gameRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(gameINitializer)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonValue))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        EntityWinnerGame winnerGame = objectMapper.readValue(jsonResponse, EntityWinnerGame.class);

        assertThat(winnerGame).isNotNull();
        assertThat(winnerGame.getIdRound()).isEqualTo(1);
        assertThat(winnerGame.getInitializerGame().getIdGame()).isEqualTo(1);
    }

    @Test
    public void gamePlayBithBadRequestException() throws Exception {

        GameRequest gameRequest = GameRequest.builder().build();
        gameRequest.setDeckId("j8the061tg17");
        gameRequest.setCount(53);
        String JsonValue = objectMapper.writeValueAsString(gameRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(gameINitializer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonValue))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        assertEquals(GameConstants.ERROR_DRAW_DECK, responseContent);
    }
}