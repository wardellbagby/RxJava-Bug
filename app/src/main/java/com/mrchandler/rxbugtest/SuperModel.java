package com.mrchandler.rxbugtest;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author Wardell
 */
public class SuperModel {
    public List<String> makeNetworkCall() throws IOException {
        List<String> results = new LinkedList<>();
        OkHttpClient client = new OkHttpClient.Builder().build();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=1").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=5").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=10").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=20").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=1").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=5").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=10").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=20").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=1").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=5").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=10").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=20").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=1").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=5").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=10").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=20").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=1").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=5").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=10").get().build()).execute();
        client.newCall(new Request.Builder().url("http://lorem-rss.herokuapp.com/feed?unit=second&interval=20").get().build()).execute();
        results.add("A Thing Happened.");
        return results;
    }
}
