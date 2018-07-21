package com.example.hedo.mtg.models;

import java.util.List;

/**
 * @author hedo
 */
public class CardsResponseBody {
    private List<Card> cards;

    /**
     * Gets the cards list.
     *
     * @return List<Card> cards.
     */
    public List<Card> getCards() {
        return cards;
    }
}
