package com.sanches.jogodecartas.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GameConstants {

    public final String SQ_TB_VENCEDORES = "sq_tb_winners";

    public final String SQ_INITIALIZER_GAME = "sq_tb_initializer_game";

    public final String ERROR_INITIALIZER_GAME = "The deckCount parameter cannot be 0, please enter a number of decks to start the game";

    public final String ERROR_NULL_DECK_COUNT = "The deckCount parameter cannot be null";

    public final String ERROR_DRAW_DECK = "Error, Number of cards remaining in deck less than requested";
}
