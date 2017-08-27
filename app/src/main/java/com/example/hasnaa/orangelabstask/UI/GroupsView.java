package com.example.hasnaa.orangelabstask.UI;

import com.example.hasnaa.orangelabstask.model.Group;

import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;
import net.grandcentrix.thirtyinch.distinctuntilchanged.DistinctUntilChanged;

import java.util.List;

/**
 * Created by Hasnaa on 27-08-2017.
 */
public interface  GroupsView extends TiView{
    @CallOnMainThread
    @DistinctUntilChanged
    void provideData(List<Group> data);
}
