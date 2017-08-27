package com.example.hasnaa.orangelabstask.UI;

import com.example.hasnaa.orangelabstask.model.Group;

import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;
import net.grandcentrix.thirtyinch.distinctuntilchanged.DistinctUntilChanged;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali on 27/08/2017.
 */

public interface PhotosView extends TiView {

    @CallOnMainThread
    @DistinctUntilChanged
    void provideData(List<String> data);

}
