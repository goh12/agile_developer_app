package com.agiledev.agiledeveloper.datacontroller;


import android.util.Log;

import com.agiledev.agiledeveloper.MainActivity;
import com.agiledev.agiledeveloper.R;
import com.agiledev.agiledeveloper.datacontroller.networking.Networking;

import org.json.JSONException;
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
    public ProjectDataController(MainActivity act) {
        this.act = act;
        client = Networking.getClient();
    }


    /**
     * Log in on server.
     * @param json holding project token.
     * @return jsonResponse Response body
     */
    public void login(JSONObject json) {
        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/projects/login")
                .post(body)
                .build();

        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e("Networking", "Login failed");
                // TODO call parser for error?
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String t = response.body().string();
                // TODO call parser with response
            }
        });

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

