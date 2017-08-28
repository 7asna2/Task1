package com.example.hasnaa.orangelabstask.UI.GroupsUI;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.hasnaa.orangelabstask.model.Group;
import com.example.hasnaa.orangelabstask.model.GroupsSearch;


import net.grandcentrix.thirtyinch.TiConfiguration;
import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hasnaa on 27-08-2017.
 */
public class GroupsPresenter extends TiPresenter<GroupsView> {

    public final String LOG_TAG = this.getClass().getSimpleName();
    List<Group> groupsList = null;
    private GroupModel groupsModel;

    public static final TiConfiguration PRESENTER_CONFIG =
            new TiConfiguration.Builder()
                    .setRetainPresenterEnabled(true)
                    .setCallOnMainThreadInterceptorEnabled(true)
                    .setDistinctUntilChangedInterceptorEnabled(true)
                    .build();

    public GroupsPresenter() {
        super(PRESENTER_CONFIG);
        this.groupsModel = new GroupModel();
    }

    @Override
    protected void onAttachView(@NonNull GroupsView view) {
        Log.i(LOG_TAG,"func OnattachView called");
        super.onAttachView(view);
        if(groupsList!=null){
            getView().provideData(groupsList);
        }
    }

    public void search(String text) {
        Log.i(LOG_TAG,"func Search ");
        Log.d(LOG_TAG,"params: text"+text);
        groupsModel.setGroupsSearchCompleted(new GroupsSearchCompleted() {
            @Override
            public void onDownload(GroupsSearch groupsSearch) {
                groupsList = new ArrayList<Group>();
                for (Group group:groupsSearch.getGroups().getGroup()){
                    groupsList.add(group);
                }
                getView().provideData(groupsList);
            }

            @Override
            public void onError(String error) {
                getView().showErrorMsg(error);
            }
        });
        groupsModel.searchGroups(text);
    }


}
