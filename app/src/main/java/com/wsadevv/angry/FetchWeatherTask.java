package com.wsadevv.angry;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by willi on 06/02/2017.
 */



public class FetchWeatherTask extends AsyncTask<Void, Void, String> {
    ImageButton sync;
    @Override
    protected String doInBackground(Void... params) {
        String forecastJSON;
        final  String LOG_TAG = "MY_TAG";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?lat=-3.894&lon=-42.69424&mode=json&APPID=81f1130147c42be3457940e3a143f925");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if(inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine())!= null){
                stringBuffer.append(line+"\n");

            }
            if(stringBuffer.length() == 0){
                forecastJSON = null;
            }
            forecastJSON = stringBuffer.toString();
            Log.v(LOG_TAG,forecastJSON);


        }catch (MalformedURLException m){
            Log.e(LOG_TAG,m.getMessage());
        } catch (IOException e) {
            Log.e(LOG_TAG,e.getMessage());
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG,e.getMessage());
                }
            }
        }

        return null;

    }
    @TargetApi(19)
    public List<String> JSONParser(String forecast) throws JSONException{
        List<String> forecasts;

        JSONObject forecastCollection = new JSONObject(forecast);
        JSONArray array = new JSONArray(forecastCollection.getJSONArray("list"));


        return null;

    }
}



