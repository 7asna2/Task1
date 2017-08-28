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

import com.example.hasnaa.orangelabstask.PhotosRecyclerViewAdapter;
import com.example.hasnaa.orangelabstask.R;

import net.grandcentrix.thirtyinch.TiFragment;

import java.util.List;



public class PhotosFragment_ extends TiFragment<PhotosPresenter, PhotosView> implements PhotosView, SearchView.OnQueryTextListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
//    private final static int IMAGE_SEARCH_LOADER = 10;
//    private static final String SEARCH_QUERY_URL_EXTRA = "query";

    RecyclerView recyclerView;
    TextView emptyView;
    SearchView searchView;
    ProgressBar loadingIndicator;

    public PhotosFragment_() {
    }

    public static PhotosFragment_ newInstance() {
        return new PhotosFragment_();
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
//        searchView = (SearchView) view.findViewById(R.id.search1);
        searchView = (SearchView)getActivity().findViewById(R.id.search1);

        emptyView = (TextView) view.findViewById(R.id.empty_view);
        loadingIndicator = (ProgressBar) view.findViewById(R.id.loading_indicator);
        searchView.setOnQueryTextListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    @Override
    public void provideData(List<String> data) {
        emptyView.setVisibility(View.GONE);
        PhotosRecyclerViewAdapter adapter = new PhotosRecyclerViewAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMsg() {
        Log.e(LOG_TAG,"error getting data");
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        loadingIndicator.setVisibility(View.GONE);
        getPresenter().search(query.trim());

        //hide keyboard
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
