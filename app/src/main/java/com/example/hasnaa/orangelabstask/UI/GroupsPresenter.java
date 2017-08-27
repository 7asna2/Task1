package com.example.hasnaa.orangelabstask.UI;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.hasnaa.orangelabstask.model.Group;
import com.example.hasnaa.orangelabstask.model.GroupsSearch;
import com.example.hasnaa.orangelabstask.model.Photo;

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
 * Created by Hasnaa on 27-08-2017.
 */
public class GroupsPresenter extends TiPresenter<GroupsView> {

    public final String LOG_TAG = this.getClass().getSimpleName();

    private Service service;
    private Call<GroupsSearch> call;

    public static final TiConfiguration PRESENTER_CONFIG =
            new TiConfiguration.Builder()
                    .setRetainPresenterEnabled(true)
                    .setCallOnMainThreadInterceptorEnabled(true)
                    .setDistinctUntilChangedInterceptorEnabled(true)
                    .build();

    public GroupsPresenter (){super(PRESENTER_CONFIG);}

    @Override
    protected void onAttachView(@NonNull GroupsView view) {
        super.onAttachView(view);
    }

    @Override
    protected void onDetachView() {
        super.onDetachView();
        if(!call.isCanceled())
            call.cancel();
    }

    public void search(String text){
        final String baseUrl = "https://api.flickr.com/services/rest/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
        call = service.GroupsList(Service.API_KEY, "flickr.groups.search", "json", "1", text);
        call.enqueue(new Callback<GroupsSearch>() {
            @Override
            public void onResponse(Call<GroupsSearch> call, Response<GroupsSearch> response) {
                List<Group> groups= new ArrayList<Group>();
                for(Group group:response.body().getGroups().getGroup()){
                    groups.add(group);
                }
                if(getView()!=null)
                getView().provideData(groups);
            }

            @Override
            public void onFailure(Call<GroupsSearch> call, Throwable t) {
                if(getView()!=null)
                    getView().provideData(null);

                Log.e(LOG_TAG,"failed to get response",t);
            }
        });


    }
}
