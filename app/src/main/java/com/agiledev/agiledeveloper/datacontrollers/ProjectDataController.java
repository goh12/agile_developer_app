package com.agiledev.agiledeveloper.datacontrollers;


import android.util.Log;

import com.agiledev.agiledeveloper.MainActivity;
import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
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
     * @return json Response body, null if failure.
     */
    public JSONObject login(JSONObject json) {
        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/projects/login")
                .post(body)
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()){
                JSONObject ret = new JSONObject();
                ret.put("status", res.body().string());
                return ret;
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("NETWORKING", "Failed to parse JSON response.");
            e.printStackTrace();
        }
        return null;
    };

    /**
     * Calls server to create a  new project.
     * @param json { name, token }
     * @return json Response body, null if failure.
     */
    public JSONObject save(JSONObject json) {
        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/projects/create")
                .post(body)
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()) {
                JSONObject ret = new JSONObject();
                ret.put("status", res.body().string());

                Log.e("NETWORKING", ret.getString("status"));
                return ret;
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("NETWORKING", "Failed to parse JSON response.");
            e.printStackTrace();
        }

        return null;
    }


    public JSONObject checkLogin() {
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/projects/")
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()) {
                JSONObject ret = new JSONObject();
                ret.put("status", res.body().string());

                Log.e("NETWORKING", ret.getString("status"));
                return ret;
            }

            Log.e("NETWORKING", "Error " + res);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("NETWORKING", "Failed to parse JSON response.");
            e.printStackTrace();
        }

        return null;
    }


}

