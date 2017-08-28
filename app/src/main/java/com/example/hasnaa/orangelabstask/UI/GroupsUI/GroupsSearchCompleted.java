package com.example.hasnaa.orangelabstask.UI.GroupsUI;

import com.example.hasnaa.orangelabstask.model.GroupsSearch;
import com.example.hasnaa.orangelabstask.model.PhotosSearch;

/**
 * Created by Hasnaa on 28-08-2017.
 */
public interface GroupsSearchCompleted {
    void onDownload(GroupsSearch photosSearch);
    void onError(String error);

}
