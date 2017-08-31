package com.example.hasnaa.orangelabstask.UI.PhotosUI;

import android.util.Log;

import com.example.hasnaa.orangelabstask.Service;
import com.example.hasnaa.orangelabstask.Singleton;
import com.example.hasnaa.orangelabstask.model.PhotosSearch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hasnaa on 28-08-2017.
 */
public class PhotoModel {
    private final String LOG_TAG= this.getClass().getSimpleName();
    private Call<PhotosSearch> call;
    private SearchCompleted photosSearchCompleted;

    public SearchCompleted getPhotosSearchCompleted() {
        return photosSearchCompleted;
    }

    public void setPhotosSearchCompleted(SearchCompleted photosSearchCompleted) {
        this.photosSearchCompleted = photosSearchCompleted;
    }


    public void searchPhotos(String query) {
        Singleton singleton = Singleton.getInstance();

        Log.i(LOG_TAG,"Func SearchPhotos called");
        Log.d(LOG_TAG,"parms query: " +query);
//        service = singleton.getRetrofit().create(Service.class);
        call = singleton.getService().photosList(Service.API_KEY, "flickr.photos.search", "json", "1", query);
        call.enqueue(new Callback<PhotosSearch>() {
            @Override
            public void onResponse(Call<PhotosSearch> call, Response<PhotosSearch> response) {
//                urls = new ArrayList<String>();
                Log.i(LOG_TAG,response.toString());
                if (response.body().getPhotos() != null) {
                    photosSearchCompleted.onDownload(response.body());
                } else {
                    photosSearchCompleted.onError(response.body().getStat());
                }

            }

            @Override
            public void onFailure(Call<PhotosSearch> call, Throwable t) {
                photosSearchCompleted.onError(t.getMessage());
            }
        });
    }
}


