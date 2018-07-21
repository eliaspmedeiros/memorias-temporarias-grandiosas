package com.example.hedo.mtg.utils;

import com.example.hedo.mtg.models.Card;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hedo
 */
public class FavoritesManager {
    private static final String FAVORITES_KEY = "favorites";
    private static List<Card> favoriteCards;

    /**
     * Fetches all favorite cards from SharedPreferences.
     */
    public static void fetchAll() {
        String favoritesString = SharedPreferencesManager.fetch(FAVORITES_KEY);

        if (favoritesString != null) {
            favoriteCards = new Gson().fromJson(favoritesString, new TypeToken<List<Card>>() {
            }.getType());
        } else {
            favoriteCards = new ArrayList<>();
        }
    }

    /**
     * Saves all favorite cards on SharedPreferences.
     */
    public static void saveAll() {
        String favoritesString = new Gson().toJson(favoriteCards);
        SharedPreferencesManager.save(FAVORITES_KEY, favoritesString);
    }

    /**
     * Removes one card from the favorites and updates the saved ones on SharedPreferences.
     *
     * @param card Card to be removed.
     */
    public static void removeOne(Card card) {
        favoriteCards.removeIf(c -> c.equals(card));
        saveAll();
    }

    /**
     * Adds one card to the favorites.
     *
     * @param card Card to be added.
     */
    public static void addOne(Card card) {
        favoriteCards.add(card);
        saveAll();
    }

    /**
     * Clears the favorite entry from SharedPreferences and clears the static card list.
     */
    public static void clearAll() {
        favoriteCards = new ArrayList<>();
        SharedPreferencesManager.clear(FAVORITES_KEY);
    }

    /**
     * Checks whether a card is favorite.
     *
     * @param card Card.
     * @return true case it is, false otherwise.
     */
    public static boolean checkIfFavorite(Card card) {
        return favoriteCards.contains(card);
    }

    /**
     * Gets the favorite cards.
     *
     * @return List<Card> cards.
     */
    public static List<Card> getFavoriteCards() {
        return favoriteCards;
    }
}
