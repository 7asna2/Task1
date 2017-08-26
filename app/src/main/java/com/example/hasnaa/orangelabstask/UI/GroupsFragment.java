package com.example.hasnaa.orangelabstask.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hasnaa.orangelabstask.GroupDetails;
import com.example.hasnaa.orangelabstask.GroupsRecyclerViewAdapter;
import com.example.hasnaa.orangelabstask.PhotosRecyclerViewAdapter;
import com.example.hasnaa.orangelabstask.R;
import com.example.hasnaa.orangelabstask.Utiles;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Hasnaa on 22-08-2017.
 */
public class GroupsFragment  extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<GroupDetails>>
        ,SearchView.OnQueryTextListener{

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final static int GROUP_SEARCH_LOADER = 101;
    private static final String SEARCH_QUERY_URL_EXTRA = "group query";



    RecyclerView recyclerView;

    TextView emptyView;

    SearchView searchView;
    ProgressBar loadingIndicator;

    public GroupsFragment() {
    }

    public static GroupsFragment newInstance() {
        GroupsFragment fragment=  new GroupsFragment();
        return fragment;
    }

    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment, container, false);

        searchView = (SearchView) view.findViewById(R.id.search1);
//        searchView = (SearchView)getActivity().findViewById(R.id.search1);

        emptyView = (TextView) view.findViewById(R.id.empty_view);
        loadingIndicator = (ProgressBar) view.findViewById(R.id.loading_indicator);
        searchView.setOnQueryTextListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeSearchQuery(String tag) throws MalformedURLException {

        String url = Utiles.getGroupSearchUrl(tag);
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY_URL_EXTRA, url);
        LoaderManager loaderManager = getActivity().getSupportLoaderManager();
        Loader<String> searchLoader = loaderManager.getLoader(GROUP_SEARCH_LOADER);
        if (searchLoader == null) {
            loaderManager.initLoader( GROUP_SEARCH_LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader( GROUP_SEARCH_LOADER, queryBundle, this);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            makeSearchQuery(query.trim());
        } catch (MalformedURLException e) {
            e.printStackTrace();

            return false;
        }
        getActivity().getSupportLoaderManager().initLoader( GROUP_SEARCH_LOADER, null, GroupsFragment.this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public Loader<ArrayList<GroupDetails>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<ArrayList<GroupDetails>>(getActivity()) {
            @Override
            protected void onStartLoading() {

                if (args == null) {
                    return;
                }
                loadingIndicator.setVisibility(View.VISIBLE);
                forceLoad();
            }
            @Override
            public ArrayList<GroupDetails> loadInBackground() {
                ArrayList<GroupDetails> Groups;
                String searchQueryUrlString = args.getString(SEARCH_QUERY_URL_EXTRA);
                if (searchQueryUrlString == null) {
                    return null;
                }

                try {
                    URL url = new URL(searchQueryUrlString);
                    String searchResults = Utiles.getResponseFromHttpUrl(url);
                    Groups = Utiles.parseGroupJsonResponseString(searchResults);

                } catch (IOException e) {
                    Log.e(LOG_TAG,e.getMessage());
                    e.printStackTrace();
                    return null;
                } catch (JSONException e) {
                    Log.e(LOG_TAG,e.getMessage());
                    e.printStackTrace();
                    return null;
                }

                return Groups;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<GroupDetails>> loader, ArrayList<GroupDetails> data) {
        loadingIndicator.setVisibility(View.INVISIBLE);
        if (data == null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            GroupsRecyclerViewAdapter adapter = new GroupsRecyclerViewAdapter(getContext(), data);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<GroupDetails>> loader) {

    }


}

