package com.agiledev.agiledeveloper.datacontrollers.networking;

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class Networking {

    private static OkHttpClient client;
    private static PersistentCookieJar cookieJar;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Return client.
     * @return
     */
    public static OkHttpClient getClient() {
        return client;
    }

    /**
     * Setup OkHttpClient singleton and cookie jar.
     * @param context
     */
    public static void setup(Context context) {
        if (client != null) return;

        cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(cookieJar).build();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        client = builder.build();
    }

    /**
     * Clear cookies. Effectively logging out client.
     */
    public static void clearCookies() {
        if(cookieJar == null) return;
        cookieJar.clear();
    }

    public static PersistentCookieJar getCookieJar() {
        return cookieJar;
    }

}
