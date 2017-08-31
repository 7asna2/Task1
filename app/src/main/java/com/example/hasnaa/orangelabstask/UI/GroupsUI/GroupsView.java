package com.example.hasnaa.orangelabstask.UI.GroupsUI;

import com.example.hasnaa.orangelabstask.model.Group;

import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;
import net.grandcentrix.thirtyinch.distinctuntilchanged.DistinctUntilChanged;

import java.util.List;


public interface  GroupsView extends TiView{
    @CallOnMainThread
    @DistinctUntilChanged
    void provideData(List<Group> data);
    @CallOnMainThread
    @DistinctUntilChanged
    void showErrorMsg(String msg);

}
