package com.sanches.jogodecartas.calculatewinner;

import com.sanches.jogodecartas.controller.response.Cards;
import com.sanches.jogodecartas.controller.response.Hand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@Slf4j
@Configuration
public class CalculateAndReturnWinner {

    public int getMaxScore(int maxScore, List<Hand> winners, Hand hand) {
        int score = calculateScore(hand.getCards());
        if (score > maxScore) {
            maxScore = score;
            winners.clear();
            winners.add(hand);
        } else if (score == maxScore) {
            winners.add(hand);
        }
        return maxScore;
    }

    private static final Map<String, Integer> cardValues = Map.ofEntries(

            entry("ACE", 1),
            entry("KING", 13),
            entry("QUEEN", 12),
            entry("JACK", 11)
    );

    private int calculateScore(List<Cards> cards) {
        int score = 0;
        for (Cards card : cards) {
            if(cardValues.containsKey(card.getValue())){
                score += cardValues.get(card.getValue());
            } else {
                score += Integer.parseInt(String.valueOf(card.getValue()));
            }
        }
        return score;
    }
}
