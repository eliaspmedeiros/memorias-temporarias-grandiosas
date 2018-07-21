package com.example.hedo.mtg.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author hedo
 */
public class SharedPreferencesManager {
    private static final String PREFERENCES_NAME = "MTG_SHARED_PREFS";

    private static SharedPreferences sharedPreferences;

    /**
     * Gets its SharedPreferences instance from a given context.
     *
     * @param context Context.
     */
    public static void bind(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
    }

    /**
     * Saves a key-value pair to Shared Preferences.
     *
     * @param key   String identifier key.
     * @param value String value.
     */
    public static void save(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);

        editor.apply();
    }

    /**
     * Fetches a string from Shared Preferences.
     *
     * @param key String key with which the saved data was identified.
     * @return data String if the key exists, null otherwise.
     */
    public static String fetch(String key) {
        return sharedPreferences.getString(key, null);
    }

    /**
     * Clears a key from Shared Preferences.
     *
     * @param key to be cleared.
     */
    public static void clear(String key) {
        sharedPreferences.edit().remove(key).apply();
    }
}
