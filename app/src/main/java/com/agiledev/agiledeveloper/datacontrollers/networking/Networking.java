package com.agiledev.agiledeveloper.datacontrollers.networking;

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class Networking {
    private Networking() {};

    private static OkHttpClient client;
    private static ClearableCookieJar cookieJar;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Return client.
     * @return
     */
    public static OkHttpClient getClient() {
        if (client == null)
            throw new IllegalStateException("Networking.setup() should have been called at app initialisation");

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

        client = new OkHttpClient.Builder().cookieJar(cookieJar).build();
    }

    /**
     * Clear cookies. Effectively logging out client.
     */
    public static void clearCookies() {
        if(cookieJar == null) return;
        cookieJar.clear();
    }

}
