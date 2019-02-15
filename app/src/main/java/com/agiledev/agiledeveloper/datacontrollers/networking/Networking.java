package com.agiledev.agiledeveloper.datacontrollers.networking;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class Networking {
    private static OkHttpClient client;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    public static OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient();
        }

        return client;
    }

}
