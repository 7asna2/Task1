package com.example.hasnaa.orangelabstask.UI;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.hasnaa.orangelabstask.model.GroupsSearch;
import com.example.hasnaa.orangelabstask.model.Photo;
import com.example.hasnaa.orangelabstask.model.PhotosSearch;

import net.grandcentrix.thirtyinch.TiConfiguration;
import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ali on 27/08/2017.
 */

public class PhotosPresenter extends TiPresenter<PhotosView> {

    public final String LOG_TAG = this.getClass().getSimpleName();

    private Service service;
    private Call<PhotosSearch> call;

    public static final TiConfiguration PRESENTER_CONFIG =
            new TiConfiguration.Builder()
                    .setRetainPresenterEnabled(true)
                    .setCallOnMainThreadInterceptorEnabled(true)
                    .setDistinctUntilChangedInterceptorEnabled(true)
                    .build();

    public PhotosPresenter() {
        super(PRESENTER_CONFIG);
    }

    @Override
    protected void onAttachView(@NonNull PhotosView view) {
        super.onAttachView(view);
    }
    @Override
    protected void onDetachView() {
        super.onDetachView();
        if(!call.isCanceled())
            call.cancel();
    }

    public void search(String text) {
        Singleton singleton = Singleton.getInstance();

//        service = singleton.getRetrofit().create(Service.class);
        call = singleton.getService().photosList(Service.API_KEY, "flickr.photos.search", "json", "1", text);
        call.enqueue(new Callback<PhotosSearch>() {
            @Override
            public void onResponse(Call<PhotosSearch> call, Response<PhotosSearch> response) {
                List<String> urls= new ArrayList<String>();
                for(Photo p:response.body().getPhotos().getPhoto()){
                    urls.add(p.toString());
                }
                if(getView()!=null)
                    getView().provideData(urls);
            }

            @Override
            public void onFailure(Call<PhotosSearch> call, Throwable t) {
                if(getView()!=null)
                    getView().provideData(null);

                Log.e(LOG_TAG,"failed to get response",t);
            }
        });
    }

}
