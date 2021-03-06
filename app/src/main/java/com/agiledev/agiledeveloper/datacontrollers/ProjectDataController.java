package com.agiledev.agiledeveloper.datacontrollers;


import android.util.JsonReader;
import android.util.Log;

import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProjectDataController {
    private OkHttpClient client;  //OkHttpClient singleton.

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
                String jsonString = res.body().string();
                Log.e("NETWORKING", jsonString);
                JSONObject ret = new JSONObject(jsonString);
                return ret;
            }

            Log.e("NETWORKING", res.toString());
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
    public JSONObject create(JSONObject json) {
        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/projects/create")
                .post(body)
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()) {
                JSONObject ret = new JSONObject(res.body().string());

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

    /**
     * Fall sér um að spyrja server hvort client token sé valid til notkunar.
     * @return JSONObject response
     */
    public JSONObject checkLogin() {
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/projects/")
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()) {
                Log.e("DATAPARSER", res.toString());
                String jsonResponse = res.body().string();
                Log.w("JSON", jsonResponse);
                JSONObject ret = new JSONObject(jsonResponse);

                return ret;
            }

            Log.e("NETWORKING", "Error " + res);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("NETWORKING", "Failed to parse JSON response.");
            e.printStackTrace();
        } catch (Exception e) {
            JSONObject ob = new JSONObject();

            try {
                ob.put("success", false);
                ob.put("message", "Server doing the sleepies");
            } catch (JSONException je) {

            }

            return ob;
        }

        return null;
    }

}

