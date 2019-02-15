package com.agiledev.agiledeveloper.datacontrollers;


import android.util.Log;

import com.agiledev.agiledeveloper.MainActivity;
import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProjectDataController {
    private OkHttpClient client;  //OkHttpClient singleton.
    private MainActivity act;

    /**
     * Constructor, fetches singleton OkHttpClient.
     */
    public ProjectDataController() {
        client = Networking.getClient();
    }


    /**
     * Log in on server.
     * @param json holding project token.
     * @return jsonResponse Response body
     */
    public String login(JSONObject json) {
        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/projects/login")
                .post(body)
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()){
                return res.body().string();
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    };


    public void save(JSONObject json) {
        // TODO implement
        Log.e("IMPLEMENTATION", "No implementation yet. ProjectDataController::save(JSONObject)");
    }

    public void delete(JSONObject json) {
        // TODO implement
        Log.e("IMPLEMENTATION", "No implementation yet. ProjectDataController::delete(JSONObject)");
    }

}

