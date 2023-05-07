package com.sanches.jogodecartas.successTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanches.jogodecartas.entity.EntityInitializerGame;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@AutoConfigureMockMvc
public class IntagratioSuccessTest {

    @Value("${url-que-gera-um-novo-deck.url}")
    private String urlQueGeraNovoDeck;
    @Value("${url-que-lista-decks-validos.url}")
    private String urlListaDecks;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    IntagratioSuccessTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void mustCreateANewNeckToStartAGame() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(urlQueGeraNovoDeck)
                        .param("deck_count", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        EntityInitializerGame entityReturn = objectMapper.readValue(jsonResponse, EntityInitializerGame.class);

        assertThat(entityReturn).isNotNull();
        assertThat(entityReturn.getRemaining()).isEqualTo(104);
        assertThat(entityReturn.getIdGame()).isEqualTo(14);
    }

    @Test
    public void shouldReturnAListOfAllDecksRegisteredInTheDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(urlListaDecks))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<EntityInitializerGame> entityList = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        Optional<Integer> remaining = Optional.of(104);

        assertThat(entityList).isNotNull();
        assertEquals(4, entityList.size());
        assertEquals(remaining, Optional.ofNullable(entityList.get(0).getRemaining()));
        assertThat(entityList.get(2).getIdGame()).isEqualTo(13);
    }
}