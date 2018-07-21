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

    public static Card fromJson(String json) {
        return new Gson().fromJson(json, Card.class);
    }

    public String getName() {
        return name;
    }

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

    public String getType() {
        return type;
    }

    public String getRarity() {
        return rarity;
    }

    public String getArtist() {
        return artist;
    }

    public String getPower() {
        return power;
    }

    public String getToughness() {
        return toughness;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getId() {
        return id;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return id != null ? id.equals(card.id) : card.id == null;
    }
}