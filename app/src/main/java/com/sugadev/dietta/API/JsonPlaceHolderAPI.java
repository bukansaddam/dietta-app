package com.sugadev.dietta.API;

import com.sugadev.dietta.Login;
import com.sugadev.dietta.User.Culinary.Model.Culinary;
import com.sugadev.dietta.User.Diary.Model.Diary;
import com.sugadev.dietta.User.Diary.Model.DiaryDetail;
import com.sugadev.dietta.User.History.Model.HistoryDetail;
import com.sugadev.dietta.User.History.Model.History;
import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChild;
import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChildDetail;
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
    Call<Culinary> addCulinaries(@Body Culinary culinary);
    @PUT("culinary/update/{id}")
    Call<Culinary> updateMakanan(@Path("id") int id, @Body Culinary culinary);
    @DELETE("culinary/delete/{id}")
    Call<Culinary> deleteMakanan(@Path("id") int id);


    //Diary
    @GET("diary/user/{id}")
    Call<List<DiaryDetail>> getDiary(@Header("Authorization") String token, @Path("id") int id);
    @POST("diary/add")
    Call<Diary> addDiary(@Header("Authorization") String token, @Body Diary diary);
    @DELETE("diary/delete/{id}")
    Call<Diary> deleteDiary(@Header("Authorization") String token, @Path("id") int id);


    //Video
    @GET("video/category/gym")
    Call<List<Video>> getGym();
    @GET("video/all")
    Call<List<Video>> getVideo();
    @GET("video/category/yoga")
    Call<List<Video>> getYoga();
    @GET("video/category/cardio")
    Call<List<Video>> getCardio();
    @GET("video/category/pilates")
    Call<List<Video>> getPilates();
    @POST("video/add")
    Call<Video> addVideo(@Body Video video);
    @PUT("video/update/{id}")
    Call<Video> updateVideo(@Path("id") int id, @Body Video video);
    @DELETE("video/delete/{id}")
    Call<Video> deleteVideo(@Path("id") int id);


    //Schedule
    @GET("schedule/parent/user/{id}")
    Call<List<ScheduleParentDetail>> getScheduleParent(@Header("Authorization") String token, @Path("id") int idUser);
    @GET("schedule/child/all/{id}")
    Call<List<ScheduleChildDetail>> getScheduleChild(@Header("Authorization") String token, @Path("id") int idParent);
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
    @GET("history/user/{id}")
    Call<List<HistoryDetail>> getHistory(@Path("id") int id);
    @POST("history/add")
    Call<History> addHistory(@Body History history);
























}
