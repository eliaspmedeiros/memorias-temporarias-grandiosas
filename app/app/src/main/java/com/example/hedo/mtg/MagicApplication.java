package com.example.hedo.mtg;

import android.app.Application;

import com.example.hedo.mtg.utils.FavoritesManager;
import com.example.hedo.mtg.utils.SharedPreferencesManager;

/**
 * @author hedo
 */
public class MagicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.bind(this);
        FavoritesManager.fetchAll();
    }
}
