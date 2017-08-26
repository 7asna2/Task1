package com.example.hasnaa.orangelabstask;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Hasnaa on 14-08-2017.
 */
public final class Utiles {

    private static final String LOG_TAG = "Utiles";
    private final static String API_KEY = "f7c50d0442f77c4c86dd60e696ac0973";

    public static ArrayList<String> parsePhotoJsonResponseString (String response) throws JSONException {

        Log.i(LOG_TAG,"fun called: parsePhotoJsonResponseString");
        Log.d(LOG_TAG,"parsePhotoJsonResponseString param: response="+response);

        final String PHOTOS = "photos";
        final String PHOTO = "photo";
        final String ID = "id";
        final String SECRET = "secret";
        final String SERVER = "server";
        final String FARM = "farm";
        ArrayList<String> al = new ArrayList<>();
        JSONObject responseJson = new JSONObject(response);
        responseJson = responseJson.getJSONObject(PHOTOS);
        JSONArray photosArray = responseJson.getJSONArray(PHOTO);

        for (int i= 0 ;i<photosArray.length()&&i<40 ; i++) {
            URLDetails urlDetails= new URLDetails();
            JSONObject jsonObject =photosArray.getJSONObject(i);
            urlDetails.id=(jsonObject.getString(ID));
            urlDetails.secret=(jsonObject.getString(SECRET));
            urlDetails.serverId=(jsonObject.getString(SERVER));
            urlDetails.farmId=(jsonObject.getString(FARM));

            String s="https://farm"+urlDetails.farmId+".staticflickr.com/"+urlDetails.serverId+
                    "/"+urlDetails.id+"_"+ urlDetails.secret+"_z.jpg";
            al.add(s);
        }
        return al;
    }

    public static ArrayList<GroupDetails> parseGroupJsonResponseString (String response) throws JSONException {

        Log.i(LOG_TAG,"fun called: parseGroupJsonResponseString");
        Log.d(LOG_TAG,"parseGroupJsonResponseString param: response="+response);

        final String GROUPS = "groups";
        final String GROUP = "group";
        final String NAME = "name";
        final String MEMBERS = "members";
        final String TOPIC_COUNT = "topic_count";
        ArrayList<GroupDetails> al = new ArrayList<>();
        JSONObject responseJson = new JSONObject(response);
        responseJson = responseJson.getJSONObject(GROUPS);
        JSONArray photosArray = responseJson.getJSONArray(GROUP);

        for (int i= 0 ;i<photosArray.length()&&i<40 ; i++) {
            GroupDetails groupDetails= new GroupDetails();
            JSONObject jsonObject =photosArray.getJSONObject(i);
            groupDetails.name=(jsonObject.getString(NAME));
            groupDetails.members=(jsonObject.getString(MEMBERS));
            groupDetails.topicCount=(jsonObject.getString(TOPIC_COUNT));

            al.add(groupDetails);
        }
        return al;
    }




    public static String getPhotoSearchUrl(String tag) throws MalformedURLException {

        Log.i(LOG_TAG,"fun called: getPhotoSearchUrl");
        Log.d(LOG_TAG,"getPhotoSearchUrl param: tag="+tag);
//      final String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=3a6a6a85e05a91d5dbc4b80aa95d4918&format=json&nojsoncallback=1&tags=";
        final String baseUrl = "https://api.flickr.com/services/rest/";
        Uri uri = Uri.parse(baseUrl)
                .buildUpon()
                .appendQueryParameter("api_key",API_KEY)
                .appendQueryParameter("method","flickr.photos.search")
                .appendQueryParameter("format","json")
                .appendQueryParameter("nojsoncallback","1")
                .appendQueryParameter("tags",tag)
                .build();
        return uri.toString();

    }

    public static String getGroupSearchUrl(String text) throws MalformedURLException {

        Log.i(LOG_TAG,"fun called: getPhotoSearchUrl");
        Log.d(LOG_TAG,"getPhotoSearchUrl param: text="+text);
//      final String url = "https://api.flickr.com/services/rest/?method=flickr.groups.search&api_key=4c1b83ccd9d6c6e6b701ad6043fd69bd&text=electronics&format=json&nojsoncallback=1";
        final String baseUrl = "https://api.flickr.com/services/rest/";
        Uri uri = Uri.parse(baseUrl)
                .buildUpon()
                .appendQueryParameter("api_key",API_KEY)
                .appendQueryParameter("method","flickr.groups.search")
                .appendQueryParameter("format","json")
                .appendQueryParameter("nojsoncallback","1")
                .appendQueryParameter("text",text)
                .build();
        return uri.toString();

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}

