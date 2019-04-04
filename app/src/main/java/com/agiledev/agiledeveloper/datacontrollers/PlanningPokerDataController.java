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

public class PlanningPokerDataController {

    private OkHttpClient client;  //OkHttpClient singleton.

    /**
     * Constructor, fetches singleton OkHttpClient.
     */
    public  PlanningPokerDataController() {
        client = Networking.getClient();
    }

    /**
     * Sér um að senda request á server sem býr til nýtt planning poker estimate
     * @param json
     * @return
     */
    public JSONObject create(JSONObject json) {

        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/planningpoker/estimate")
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
     * Sér um að senda delete request á server til að eyða planning poker estimate
     * @param json
     * @return
     */
    public JSONObject delete(JSONObject json) {

        RequestBody body = RequestBody.create(Networking.MEDIA_TYPE_JSON, json.toString());
        Request req = new Request.Builder()
                .url("https://agiledevhb.herokuapp.com/api/planningpoker/delete")
                .delete(body)
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.isSuccessful()) {
                String jsonString = res.body().string();

                JSONObject ret = new JSONObject(jsonString);
                return ret;
            } else {
                Log.e("ERROR", res.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
