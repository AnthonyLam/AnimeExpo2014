package com.lamapress.animeexpo2014.axapp.network;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lamapress.animeexpo2014.axapp.R;

/**
 * Created by Anthony on 6/4/2014.
 */
public class NetworkHandler{


    public ArrayAdapter<String> data;
    public String test = "Default";

    public NetworkHandler(){
        //data.add("hello");
    }

    /* TODO: Set up Deployd Collections
       TODO: Implement loading and completion callback
     */
    public void load(Context context,String jsonFile){
         Ion.with(context)
                .load(R.string.deployd_server_ip +":" +R.string.deployd_server_port + "/" + jsonFile )
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            test = e.getMessage();
                        }
                        test = result.get(2).getAsJsonObject().get("myToast").toString();
                    }
                });
    }

    public String convertToJson(Object object){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        return gson.toJson(object);
    }
}
