package com.mrchandler.rxbugtest;

import android.util.Log;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Wardell
 */
public class SuperModel {
    public List<String> makeNetworkCall() throws IOException {
        List<String> results = new LinkedList<>();
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.e("First", "I'm intercepting ya.");
                return chain.proceed(chain.request());
            }
        }).addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.e("Second", "I'm intercepting ya.");
                return chain.proceed(chain.request());
            }
        }).build();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=1").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=5").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=10").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=20").get().build()).execute();
        results.add("A Thing Happened.");
        return results;
    }
}
