package com.example.hasnaa.orangelabstask.UI;

import net.grandcentrix.thirtyinch.TiConfiguration;
import net.grandcentrix.thirtyinch.TiPresenter;


public class MainPresenter extends TiPresenter<MainView> implements MainView {
    public static final TiConfiguration PRESENTER_CONFIG =
            new TiConfiguration.Builder()
                    .setRetainPresenterEnabled(true)
                    .setCallOnMainThreadInterceptorEnabled(true)
                    .setDistinctUntilChangedInterceptorEnabled(true)
                    .build();
    public MainPresenter(){
        super(PRESENTER_CONFIG);
    }
}
