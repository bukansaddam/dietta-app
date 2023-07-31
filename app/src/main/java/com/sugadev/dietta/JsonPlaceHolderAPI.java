package com.sugadev.dietta;

import com.sugadev.dietta.User.Culinary.Culinary;
import com.sugadev.dietta.User.Video.Model.Video;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderAPI {

    @GET("culinary/all")
    Call<List<Culinary>> getCulinary();

    @GET("video/all")
    Call<List<Video>> getVideo();

    @GET("video/category/gym")
    Call<List<Video>> getGym();

    @GET("video/category/cardio")
    Call<List<Video>> getCardio();

    @GET("video/category/yoga")
    Call<List<Video>> getYoga();

    @GET("video/category/pilates")
    Call<List<Video>> getPilates();

    @POST("culinary/add")
    Call<Culinary> addCulinaries(@Body Culinary culinary);

    @POST("video/add")
    Call<Video> addVideo(@Body Video video);
}
