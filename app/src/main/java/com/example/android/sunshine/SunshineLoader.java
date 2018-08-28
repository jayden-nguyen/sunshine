package com.example.android.sunshine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class SunshineLoader extends android.support.v4.content.AsyncTaskLoader<String[]> {
    Bundle args;
    Context context;
    Activity activity;
    private ProgressBar mProgressBar;
    private final static String SUNSHINE_URL = "SUNSHINE_URL";


    public SunshineLoader(Context context, Bundle args) {
        super(context);
        this.args = args;
        this.context = context;
        activity = (Activity)context;
        mProgressBar = (ProgressBar) activity.findViewById(R.id.pb_indicator);
    }

    @Override
    public String[] loadInBackground() {
        String location = SunshinePreferences.getPreferredWeatherLocation(getContext());
        try {
            URL url = NetworkUtils.buildUrl(location);
            String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(url);
            String[] simpleJsonWeatherData = OpenWeatherJsonUtils.getSimpleWeatherStringsFromJson(activity,jsonWeatherResponse);
           // Log.d("json is", simpleJsonWeatherData.toString());
            return simpleJsonWeatherData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        mProgressBar.setVisibility(View.VISIBLE);
        forceLoad();
    }
}