package com.example.hedo.mtg.api;

import com.example.hedo.mtg.models.CardsResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author hedo
 */

public interface MagicAPI {

    @GET("cards")
    Call<CardsResponseBody> getCards(@Query("colors") String colors,
                                     @Query("page") int page,
                                     @Query("pageSize") int pageSize);
}
