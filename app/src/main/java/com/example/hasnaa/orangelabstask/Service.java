package com.example.hasnaa.orangelabstask;

import com.example.hasnaa.orangelabstask.model.GroupsSearch;
import com.example.hasnaa.orangelabstask.model.PhotosSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ali on 27/08/2017.
 */

public interface Service {
    String API_KEY="532ecc9194ac0d738d0b4feee6e1775e";//"2358e9f96c8c7b9cf3cd0780b36ad845";

    @GET(".")
    Call<PhotosSearch> photosList(@Query("api_key")String api_key, @Query("method") String method,
                                        @Query("format")String format, @Query("nojsoncallback")String callback,
                                        @Query("tags")String tags);

    @GET(".")
    Call<GroupsSearch> GroupsList(@Query("api_key")String api_key, @Query("method") String method,
                                   @Query("format")String format, @Query("nojsoncallback")String callback,
                                   @Query("text")String text);



}


