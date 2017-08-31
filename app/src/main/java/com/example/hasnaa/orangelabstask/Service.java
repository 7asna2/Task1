package com.example.hasnaa.orangelabstask;

import com.example.hasnaa.orangelabstask.model.GroupsSearch;
import com.example.hasnaa.orangelabstask.model.PhotosSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface Service {
    String API_KEY="647e979532eba1076860120f81f5cb57";  // put your api_key here

    @GET(".")
    Call<PhotosSearch> photosList(@Query("api_key")String api_key, @Query("method") String method,
                                        @Query("format")String format, @Query("nojsoncallback")String callback,
                                        @Query("tags")String tags);

    @GET(".")
    Call<GroupsSearch> GroupsList(@Query("api_key")String api_key, @Query("method") String method,
                                   @Query("format")String format, @Query("nojsoncallback")String callback,
                                   @Query("text")String text);



}


