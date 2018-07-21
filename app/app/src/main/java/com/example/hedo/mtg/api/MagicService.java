package com.example.hedo.mtg.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author hedo
 */
public class MagicService {
    public static final int PAGE_SIZE = 40;
    private static MagicAPI instance;

    private final String BASE_URL = "https://api.magicthegathering.io/v1/";

    private MagicService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        instance = retrofit.create(MagicAPI.class);
    }


    public static MagicAPI getInstance() {
        if (instance == null) {
            new MagicService();
        }
        
        return instance;
    }
}
