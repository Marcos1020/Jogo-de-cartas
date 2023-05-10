package com.sanches.jogodecartas.calculatewinner;

import com.sanches.jogodecartas.controller.request.CardsRequest;
import com.sanches.jogodecartas.controller.request.HandRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@Slf4j
@Configuration
public class CalculateAndReturnWinner {

    public int getMaxScore(int maxScore, List<HandRequest> winners, HandRequest hand) {
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

    private int calculateScore(List<CardsRequest> cards) {
        int score = 0;
        for (CardsRequest card : cards) {
            if(cardValues.containsKey(card.getValue())){
                score += cardValues.get(card.getValue());
            } else {
                score += Integer.parseInt(String.valueOf(card.getValue()));
            }
        }
        return score;
    }
}
