package com.example.hasnaa.orangelabstask.UI.GroupsUI;

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

import com.example.hasnaa.orangelabstask.Adapters.GroupsRecyclerViewAdapter;
import com.example.hasnaa.orangelabstask.R;
import com.example.hasnaa.orangelabstask.UI.Searchable;
import com.example.hasnaa.orangelabstask.model.Group;

import net.grandcentrix.thirtyinch.TiFragment;

import java.util.List;

/**
 * Created by Hasnaa on 27-08-2017.
 */
public class GroupsFragment extends TiFragment<GroupsPresenter, GroupsView> implements Searchable,GroupsView{
    private final String LOG_TAG = this.getClass().getSimpleName();

    RecyclerView recyclerView;
    TextView emptyView;
    ProgressBar loadingIndicator;

    public GroupsFragment() {
    }

    public static GroupsFragment newInstance() {
        return new GroupsFragment();
    }

    @NonNull
    @Override
    public GroupsPresenter providePresenter() {
        return new GroupsPresenter();
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
    public void provideData(List<Group> data) {
        Log.i(LOG_TAG,"fun :provideData called");
        Log.d(LOG_TAG,"params data"+ data.toString());
        emptyView.setVisibility(View.GONE);
        GroupsRecyclerViewAdapter adapter = new GroupsRecyclerViewAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMsg(String error) {
        Log.e(LOG_TAG,error);

        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void search(String text) {
        Log.i(LOG_TAG,"func :onQueryTextSubmit");
        Log.d(LOG_TAG,text);
        loadingIndicator.setVisibility(View.GONE);
        getPresenter().search(text.trim());

        //hide keyboard
//        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }
}
