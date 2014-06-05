package com.lamapress.animeexpo2014.axapp.network;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by Anthony on 6/4/2014.
 */
public class NetworkHandler{


    private String DEPLOYD_IP = "http://162.243.153.109:5000";
    public ArrayAdapter<String> data;
    public String test = "Default";

    public NetworkHandler(){
        //data.add("hello");
    }

    /* TODO: Set up Deployd Collections
       TODO: Set up modularity for JSON download/parsing
       TODO: Implement loading and completion callback
     */
    public void load(Context context){
         Ion.with(context)
                .load(DEPLOYD_IP + "/testobjects")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if(e != null){
                            test = e.getMessage();
                        }
                        test = result.get(2).getAsJsonObject().get("myToast").toString();
                    }
                });
    }
}
