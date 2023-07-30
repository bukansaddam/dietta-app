package com.sugadev.dietta;

import com.sugadev.dietta.Model.Culinary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("all")
    Call<List<Culinary>> getCulinary();
}
