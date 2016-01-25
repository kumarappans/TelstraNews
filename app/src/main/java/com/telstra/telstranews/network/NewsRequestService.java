package com.telstra.telstranews.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.telstra.telstranews.R;
import com.telstra.telstranews.model.NewsDataResponse;

/**
 *
 * @author Kumarappan Subramanian
 *
 * Created on 1/21/16.
 *
 *
 *  NewsRequestService is used to call the service call by using Volley library
 *
 *  We are using GSON library to parse the response and converted it to object
 *
 *
 */
public class NewsRequestService {

    public static void callNewsRequest(final Context context, final NewsDataNetworkCallback callback) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.news_url);


        StringRequest newsDataRequestCall = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                NewsDataResponse responseObj = gson.fromJson(response, NewsDataResponse.class);

                if(responseObj != null) {
                    callback.onSuccess(responseObj);
                } else {
                    callback.onFailure(context.getResources().getString(R.string.error_message));
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(context.getResources().getString(R.string.error_message));
            }
        });
        queue.add(newsDataRequestCall);
    }






}
