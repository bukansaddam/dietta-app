package com.sugadev.dietta;

import com.sugadev.dietta.User.Culinary.Culinary;
import com.sugadev.dietta.User.Diary.Diary;
import com.sugadev.dietta.User.Diary.DiaryDetail;
import com.sugadev.dietta.User.Schedule.Model.Schedul;
import com.sugadev.dietta.User.Schedule.Model.ScheduleDetailParent;
import com.sugadev.dietta.User.Schedule.Model.Schedule;
import com.sugadev.dietta.User.Video.Model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderAPI {

    @GET("culinary/all")
    Call<List<Culinary>> getCulinary();

    @GET("video/all")
    Call<List<Video>> getVideo();

    @GET("/diary/all")
    Call<List<Diary>> getAllDiary();

    @GET("diary/user/{id}")
    Call<List<DiaryDetail>> getDiary(@Path("id") int id);

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

    @GET("schedule/parent/user/{id}")
    Call<List<ScheduleDetailParent>> getScheduleParent(@Path("id") int idUser);

    @GET("schedule/child/all/{id}")
    Call<List<Schedul>> getScheduleChild(@Path("id") int idParent);

    @PUT("video/update/{id}")
    Call<Video> updateVideo(@Path("id") int id, @Body Video video);

    @DELETE("video/delete/{id}")
    Call<Video> deleteVideo(@Path("id") int id);

    @PUT("culinary/update/{id}")
    Call<Culinary> updateMakanan(@Path("id") int id, @Body Culinary culinary);

    @DELETE("culinary/delete/{id}")
    Call<Culinary> deleteMakanan(@Path("id") int id);

    @DELETE("diary/delete/{id}")
    Call<Diary> deleteDiary(@Path("id") int id);

}
