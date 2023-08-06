package com.sugadev.dietta.API;

import com.sugadev.dietta.Login;
import com.sugadev.dietta.User.Culinary.Model.Culinary;
import com.sugadev.dietta.User.Diary.Model.Diary;
import com.sugadev.dietta.User.Diary.Model.DiaryDetail;
import com.sugadev.dietta.User.History.Model.History;
import com.sugadev.dietta.User.History.Model.HistoryChild;
import com.sugadev.dietta.User.History.Model.HistoryParent;
import com.sugadev.dietta.User.History.Model.HistoryParentDetail;
import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChild;
import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChildDetail;
import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleHistoryParent;
import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleParent;
import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleParentDetail;
import com.sugadev.dietta.User.UserProfile.Model.User;
import com.sugadev.dietta.User.Video.Model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderAPI {

    //Culinary
    @GET("culinary/all")
    Call<List<Culinary>> getCulinary(@Header("Authorization") String token);
    @POST("culinary/add")
    Call<Culinary> addCulinaries(@Header("Authorization") String token, @Body Culinary culinary);
    @PUT("culinary/update/{id}")
    Call<Culinary> updateMakanan(@Header("Authorization") String token, @Path("id") int id, @Body Culinary culinary);
    @DELETE("culinary/delete/{id}")
    Call<Culinary> deleteMakanan(@Header("Authorization") String token, @Path("id") int id);


    //Diary
    @GET("diary/user/{id}")
    Call<List<DiaryDetail>> getDiary(@Header("Authorization") String token, @Path("id") int id);
    @POST("diary/add")
    Call<Diary> addDiary(@Header("Authorization") String token, @Body Diary diary);
    @DELETE("diary/delete/{id}")
    Call<Diary> deleteDiary(@Header("Authorization") String token, @Path("id") int id);


    //Video
    @GET("video/category/gym")
    Call<List<Video>> getGym(@Header("Authorization") String token);
    @GET("video/all")
    Call<List<Video>> getVideo(@Header("Authorization") String token);
    @GET("video/category/yoga")
    Call<List<Video>> getYoga(@Header("Authorization") String token);
    @GET("video/category/cardio")
    Call<List<Video>> getCardio(@Header("Authorization") String token);
    @GET("video/category/pilates")
    Call<List<Video>> getPilates(@Header("Authorization") String token);
    @POST("video/add")
    Call<Video> addVideo(@Header("Authorization") String token, @Body Video video);
    @PUT("video/update/{id}")
    Call<Video> updateVideo(@Header("Authorization") String token, @Path("id") int id, @Body Video video);
    @DELETE("video/delete/{id}")
    Call<Video> deleteVideo(@Header("Authorization") String token, @Path("id") int id);


    //Schedule
    @GET("schedule/parent/user/{id}")
    Call<List<ScheduleParentDetail>> getScheduleParent(@Header("Authorization") String token, @Path("id") int idUser);
    @GET("schedule/child/all/{id}")
    Call<List<ScheduleChildDetail>> getScheduleChild(@Header("Authorization") String token, @Path("id") int idParent);
    @GET("schedule/prent/history/user/{id}")
    Call<List<ScheduleHistoryParent>> getScheduleHistoryParent(@Header("Authorization") String token, @Path("id") int id);
    @POST("schedule/history/parent/add")
    Call<ScheduleParent> addScheduleParent(@Header("Authorization") String token, @Body ScheduleParent scheduleParent);
    @POST("schedule/history/child/add")
    Call<ScheduleChild> addScheduleChild(@Header("Authorization") String token, @Body ScheduleChild scheduleChild);
    @DELETE("schedule/child/delete/{id}")
    Call<ScheduleChild> deleteScheduleChild(@Header("Authorization") String token, @Path("id") int id);
    @DELETE("schedule/parent/delete/{id}")
    Call<ScheduleParent> deleteScheduleParent(@Header("Authorization") String token, @Path("id") int id);

    //User
    @GET("user/{id}")
    Call<User> getUserDetail(@Header("Authorization") String token, @Path("id")int id);
    @POST("user/add")
    Call<User> addUser(@Body User user);
    @PUT("user/update/{id}")
    Call<User> updateUser(@Header("Authorization") String token, @Path("id") int id, @Body User user);

    //Login
    @POST("auth/login")
    Call<TokenApi> login(@Body Login login);

    //History
    @GET("history/get/biasa/{id}")
    Call<List<History>> getHistory(@Header("Authorization") String token, @Path("id") int id);
    @POST("history/add/biasa")
    Call<History> addHistory(@Header("Authorization") String token, @Body History history);
    @GET("history/parent/{id}")
    Call<List<HistoryParentDetail>> getHistoryParent(@Header("Authorization") String token, @Path("id") int id);
    @POST("history/parent/add")
    Call<HistoryParent> addHistoryParent(@Header("Authorization") String token, @Body HistoryParent historyParent);
























}
