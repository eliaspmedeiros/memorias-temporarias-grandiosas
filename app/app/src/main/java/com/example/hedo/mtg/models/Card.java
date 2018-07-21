package com.example.hedo.mtg.models;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author hedo
 */
public class Card implements Serializable {

    private String name, manaCost,
            type, rarity, artist,
            power, toughness, imageUrl,
            originalText, flavor, id;

    /**
     * Gets the card name.
     *
     * @return String name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the parsed mana cost.
     *
     * @return String mana cost.
     */
    public String getManaCost() {
        if (manaCost != null) {
            manaCost = manaCost.replace("{W}", "@")
                    .replace("{B}", "=")
                    .replace("{U}", "+")
                    .replace("{R}", "<")
                    .replace("{G}", ">")
                    .replace("{1}", "[")
                    .replace("{2}", "\\")
                    .replace("{3}", "]")
                    .replace("{4}", "^")
                    .replace("{5}", "_")
                    .replace("{6}", "`")
                    .replace("{7}", "{")
                    .replace("{8}", "|")
                    .replace("{9}", "}")
                    .replace("{0}", "~")
                    .replace("{X}", "#");
        }

        return manaCost;
    }

    /**
     * Gets the card type.
     *
     * @return String type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the card rarity.
     *
     * @return String rarity.
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Gets the card artist.
     *
     * @return String artist name.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Gets the card attack power.
     *
     * @return String power.
     */
    public String getPower() {
        return power;
    }

    /**
     * Gets the card toughness (HP)
     *
     * @return String toughness.
     */
    public String getToughness() {
        return toughness;
    }

    /**
     * Gets the card image url.
     *
     * @return String url.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Gets the card original text.
     *
     * @return String text.
     */
    public String getOriginalText() {
        return originalText;
    }

    /**
     * Gets the card flavor text.
     *
     * @return String flavor text.
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Gets the card id.
     *
     * @return String id.
     */
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return id != null ? id.equals(card.id) : card.id == null;
    }
}