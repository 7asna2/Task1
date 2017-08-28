package com.example.hasnaa.orangelabstask.UI.PhotosUI;

import com.example.hasnaa.orangelabstask.model.PhotosSearch;

/**
 * Created by Hasnaa on 28-08-2017.
 */
public interface PhotosSearchCompleted {
    void onDownload(PhotosSearch photosSearch);
    void onError(String error);
}

