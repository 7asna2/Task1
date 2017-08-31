package com.example.hasnaa.orangelabstask.UI.PhotosUI;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hasnaa.orangelabstask.Adapters.PhotosRecyclerViewAdapter;
import com.example.hasnaa.orangelabstask.R;
import com.example.hasnaa.orangelabstask.UI.Searchable;

import net.grandcentrix.thirtyinch.TiFragment;

import java.util.List;



public class PhotosFragment extends TiFragment<PhotosPresenter, PhotosView> implements Searchable,PhotosView{

    private final String LOG_TAG = this.getClass().getSimpleName();

    RecyclerView recyclerView;
    TextView emptyView;
    ProgressBar loadingIndicator;

    public PhotosFragment() {
    }

    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }
    @NonNull
    @Override
    public PhotosPresenter providePresenter() {
        return new PhotosPresenter();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment, container, false);
        emptyView = (TextView) view.findViewById(R.id.empty_view);
        loadingIndicator = (ProgressBar) view.findViewById(R.id.loading_indicator);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    @Override
    public void provideData(List<String> data) {
        Log.i(LOG_TAG,"fun :provideData called");
        Log.d(LOG_TAG,"params data"+ data.toString());
        emptyView.setVisibility(View.GONE);
        PhotosRecyclerViewAdapter adapter = new PhotosRecyclerViewAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg() {
        Log.e(LOG_TAG,"error getting data");
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void search(String text) {
        Log.i(LOG_TAG,"func :Search searchable interface");
        Log.d(LOG_TAG,text);
        loadingIndicator.setVisibility(View.VISIBLE);
        getPresenter().search(text.trim());

    }
}
