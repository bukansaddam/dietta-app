package com.sugadev.dietta;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.sugadev.dietta.User.Culinary.Culinary;
import com.sugadev.dietta.User.Diary.Diary;
import com.sugadev.dietta.User.Diary.DiaryDetail;
import com.sugadev.dietta.User.Video.Model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderAPI {

    @GET("culinary/all")
    Call<List<Culinary>> getCulinary();

    @GET("video/all")
    Call<List<Video>> getVideo();

    @GET("/diary/all")
    Call<List<Diary>> getAllDiary();

    @GET("diary/user/{id}")
    Call<List<DiaryDetail>> getDiary(@Path("id") int culinaryId);

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

    @POST("diary/add")
    Call<Diary> addDiary(@Body Diary diary);
}
