package com.example.hasnaa.orangelabstask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hasnaa on 27-08-2017.
 */
public class Singleton {
    private static Singleton sInstance;
    final String baseUrl = "https://api.flickr.com/services/rest/";
    private static Retrofit retrofit;
    private static Service service;


    private Singleton(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
    }  //private constructor.

    public static Singleton getInstance(){
        if (sInstance == null){ //if there is no instance available... create new one
            sInstance = new Singleton();
        }

        return sInstance;
    }

    public static Service getService (){
        return service;
    }

}
