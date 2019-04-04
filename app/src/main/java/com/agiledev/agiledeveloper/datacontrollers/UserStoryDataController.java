package com.agiledev.agiledeveloper.datacontrollers;
import android.util.Log;

import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserStoryDataController {

    private OkHttpClient client;  //OkHttpClient singleton.

    /**
     * Constructor, fetches singleton OkHttpClient.
     */
    public UserStoryDataController() {
        client = Networking.getClient();
    }


    /**
     * Sér um að senda request á server sem býr til nýja notendasögu
     * @param json
     * @return
     */
    public JSONObject create(JSONObject json) {

        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/userstory/create")
                .post(body)
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()){
                String jsonString = res.body().string();

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
    }

    /**
     * Sendir patch request til að uppfæra eina notendasögu á server
     * @param json
     * @return
     */
    public JSONObject update(JSONObject json) {

        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/userstory/edit")
                .patch(body)
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()) {
                String jsonString = res.body().string();

                JSONObject ret = new JSONObject(jsonString);
                return ret;
            } else {
                String jsonString = res.body().string();
                Log.e("UPDATE", jsonString);
                return new JSONObject(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("NETWORKING", "Failed to parse JSON response");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Sér um HTTP request til að eyða user story.
     * @param json
     * @return
     */
    public JSONObject delete(JSONObject json) {
        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/userstory/delete")
                .delete(body)
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()) {
                String jsonString = res.body().string();

                JSONObject ret = new JSONObject(jsonString);
                return ret;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Biður server um að sækja allar user stories
     * sem tilheyra projectinu sem notandi er loggaður inn á
     * @return
     */
    public  JSONObject getAll() {

        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com//api/userstories")
                .get()
                .build();

        try {
            Response res = client.newCall(req).execute();

            if (res.isSuccessful()) {
                String jsonString = res.body().string();

                JSONObject ret = new JSONObject(jsonString);
                return ret;
            } else {
                String jsonString = res.body().string();

                JSONObject ret = new JSONObject(jsonString);
                return ret;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("NETWORKING", "Failed to parse JSON response");
            e.printStackTrace();
        }

        JSONObject ob = new JSONObject();
        try {
            ob.put("success", false);
            ob.put("message", "Server error");
        } catch (JSONException e) {
            Log.e("SERVER ERROR", "Server produced error");
        }
        return ob;
    }
}
