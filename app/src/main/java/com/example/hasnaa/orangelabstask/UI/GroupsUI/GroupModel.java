package com.example.hasnaa.orangelabstask.UI.GroupsUI;

import android.util.Log;

import com.example.hasnaa.orangelabstask.Service;
import com.example.hasnaa.orangelabstask.Singleton;
import com.example.hasnaa.orangelabstask.model.GroupsSearch;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hasnaa on 28-08-2017.
 */
public class GroupModel {

    private final String LOG_TAG = this.getClass().getSimpleName() ;
    private Call<GroupsSearch> call;

    private GroupsSearchCompleted groupsSearchCompleted;

    public GroupsSearchCompleted getGroupsSearchCompleted() {
        return groupsSearchCompleted;
    }

    public void setGroupsSearchCompleted(GroupsSearchCompleted groupsSearchCompleted) {
        this.groupsSearchCompleted = groupsSearchCompleted;
    }


    public boolean searchGroups(String query) {
        Log.i(LOG_TAG,"fun : searchGroup called");
        Log.d(LOG_TAG, "params : query"+query);

        final boolean[] r = new boolean[1];
        Singleton singleton = Singleton.getInstance();
        call = singleton.getService().GroupsList(Service.API_KEY, "flickr.groups.search", "json", "1", query);
        call.enqueue(new Callback<GroupsSearch>() {
            @Override
            public void onResponse(Call<GroupsSearch> call, Response<GroupsSearch> response) {
                Log.i(LOG_TAG,response.toString());
                if (response.body().getGroups() != null) {
                    groupsSearchCompleted.onDownload(response.body());
                    r[0] =true;
                } else {
                    groupsSearchCompleted.onError(response.body().getStat());
                    Log.w(LOG_TAG,response.body().getStat()+response.message());
                    r[0] =false;
                }
            }

            @Override
            public void onFailure(Call<GroupsSearch> call, Throwable t) {
                groupsSearchCompleted.onError(t.getMessage());
                Log.w(LOG_TAG,"error in get response",t);
            }
        });
        return r[0];
    }
}
