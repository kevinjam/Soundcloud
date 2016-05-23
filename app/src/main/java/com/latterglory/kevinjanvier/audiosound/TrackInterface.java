package com.latterglory.kevinjanvier.audiosound;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kevinjanvier on 18/05/2016.
 */
public interface TrackInterface {
    @GET("/tracks?client_id=" + Config.CLIENT_ID)
    Call<List<Track>> getRecentTracks(@Query("created_at[from]") String date);
}
