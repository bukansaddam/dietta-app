package com.sugadev.dietta;

import com.sugadev.dietta.User.Culinary.Culinary;
import com.sugadev.dietta.User.Video.Model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("culinary/all")
    Call<List<Culinary>> getCulinary();

    @GET("video/category/gym")
    Call<List<Video>> getGym();

    @GET("video/category/cardio")
    Call<List<Video>> getCardio();

    @GET("video/category/yoga")
    Call<List<Video>> getYoga();

    @GET("video/category/pilates")
    Call<List<Video>> getPilates();
}
