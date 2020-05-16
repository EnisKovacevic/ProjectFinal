package com.example.project3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("{artist}/{song}")
    Call<Lyrics> getLyrics(@Path("artist") String artist, @Path("song") String song);
}
