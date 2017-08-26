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

import com.example.hasnaa.orangelabstask.PhotosRecyclerViewAdapter;
import com.example.hasnaa.orangelabstask.R;
import com.example.hasnaa.orangelabstask.Utiles;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Hasnaa on 21-08-2017.
 */
public class PhotosFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<String>>
        ,SearchView.OnQueryTextListener{

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final static int IMAGE_SEARCH_LOADER = 10;
    private static final String SEARCH_QUERY_URL_EXTRA = "query";



    RecyclerView recyclerView;

    TextView emptyView;

    SearchView searchView;
    ProgressBar loadingIndicator;

    public PhotosFragment() {
    }

    public static PhotosFragment newInstance() {
        PhotosFragment fragment=  new PhotosFragment();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeSearchQuery(String tag) throws MalformedURLException {

        String url = Utiles.getPhotoSearchUrl(tag);
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY_URL_EXTRA, url);
        LoaderManager loaderManager = getActivity().getSupportLoaderManager();
        Loader<String> searchLoader = loaderManager.getLoader(IMAGE_SEARCH_LOADER);
        if (searchLoader == null) {
            loaderManager.initLoader(IMAGE_SEARCH_LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader(IMAGE_SEARCH_LOADER, queryBundle, this);
        }
    }
    @Override
    public Loader<ArrayList<String>> onCreateLoader(int id, final Bundle args) {

        return new AsyncTaskLoader<ArrayList<String>>(getActivity()) {
            @Override
            protected void onStartLoading() {

                if (args == null) {
                    return;
                }

                loadingIndicator.setVisibility(View.VISIBLE);
//
                forceLoad();
            }

            @Override
            public ArrayList<String> loadInBackground() {
                ArrayList<String>imgUrls=new ArrayList<>();
                String searchQueryUrlString = args.getString(SEARCH_QUERY_URL_EXTRA);
                if (searchQueryUrlString == null) {
                    return null;
                }

                try {
                    URL url = new URL(searchQueryUrlString);
                    String searchResults = Utiles.getResponseFromHttpUrl(url);
                    imgUrls=Utiles.parsePhotoJsonResponseString(searchResults);

                } catch (IOException e) {
                    Log.e(LOG_TAG,e.getMessage());
                    e.printStackTrace();
                    return null;
                } catch (JSONException e) {
                    Log.e(LOG_TAG,e.getMessage());
                    e.printStackTrace();
                    return null;
                }

                return imgUrls;
            }
        };
    }



    @Override
    public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {
        loadingIndicator.setVisibility(View.INVISIBLE);
        if (data == null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            PhotosRecyclerViewAdapter adapter = new PhotosRecyclerViewAdapter(getContext(), data);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<String>> loader) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            makeSearchQuery(query.trim());
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG,e.getMessage());
            e.printStackTrace();

            return false;
        }
        getActivity().getSupportLoaderManager().initLoader(IMAGE_SEARCH_LOADER, null, PhotosFragment.this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
