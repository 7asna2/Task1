package com.example.hasnaa.orangelabstask.UI.PhotosUI;

import android.support.annotation.NonNull;

import com.example.hasnaa.orangelabstask.Service;
import com.example.hasnaa.orangelabstask.model.Photo;
import com.example.hasnaa.orangelabstask.model.PhotosSearch;

import net.grandcentrix.thirtyinch.TiConfiguration;
import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class PhotosPresenter extends TiPresenter<PhotosView> {

    public final String LOG_TAG = this.getClass().getSimpleName();

    private Service service;
    private Call<PhotosSearch> call;
    List<String> urls = null;
    private PhotoModel photosModel;

    public static final TiConfiguration PRESENTER_CONFIG =
            new TiConfiguration.Builder()
                    .setRetainPresenterEnabled(true)
                    .setCallOnMainThreadInterceptorEnabled(true)
                    .setDistinctUntilChangedInterceptorEnabled(true)
                    .build();

    public PhotosPresenter() {
        super(PRESENTER_CONFIG);
        this.photosModel = new PhotoModel();
    }

    @Override
    protected void onAttachView(@NonNull PhotosView view) {
        super.onAttachView(view);
        if(urls!=null){
            getView().provideData(urls);
        }
    }
    @Override
    protected void onDetachView() {
        super.onDetachView();
        if (call!=null)
            if(!call.isCanceled())
                call.cancel();
    }

    public void search(String text) {
        photosModel.setPhotosSearchCompleted(new PhotosSearchCompleted() {
            @Override
            public void onDownload(PhotosSearch photosSearch) {
                urls=new ArrayList<String>();
                for (Photo photo:photosSearch.getPhotos().getPhoto()){
                    urls.add(photo.getUrl());
                }
                getView().provideData(urls);
            }

            @Override
            public void onError(String error) {
                getView().showErrorMsg();
            }
        });
        photosModel.searchPhotos(text);
    }


}
