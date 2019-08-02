package com.rss.william.app.rssapp.Common;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDataHandler {

    static final String TAG = HttpDataHandler.class.getName();
    public String stream;

    public HttpDataHandler(){}

    public String getHttpDataHandler(String urlString){

        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if(httpURLConnection.getResponseCode()== HttpURLConnection.HTTP_OK){
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine() ) !=null){
                    sb.append(line);
                    stream = sb.toString();
                }
                httpURLConnection.disconnect();
            }
        }catch (Exception e){
            Log.e(TAG,e.getMessage(),e);
        }

        return stream;
    }

}
