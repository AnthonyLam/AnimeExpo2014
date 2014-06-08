package com.lamapress.animeexpo2014.axapp.network;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lamapress.animeexpo2014.axapp.R;

import java.lang.reflect.Type;

/**
 * @author Anthony Lam
 */
public class JsonHandler{


    String test = "Default";
    JsonArray array;

    public JsonHandler(){
    }

    /**
     *
     * @param context Activity context
     * @param jsonFile Name of remote JSON Object/File
     */
    public JsonArray load(final Context context,String jsonFile){
        Ion.with(context)
            //Load from "WEBSITE:PORT/JSONDATA"
            .load(R.string.deployd_server_ip + ":" + R.string.deployd_server_port + "/" + jsonFile)
            .asJsonArray()
            .setCallback(new FutureCallback<JsonArray>() {
                @Override
                public void onCompleted(Exception e, JsonArray result) {
                    if (e != null) {
                        test = e.getMessage();
                        Toast.makeText(context,"Error loading forom server",Toast.LENGTH_LONG);
                    }
                    array = result;
                }
            });
        return array;
    }

    /**
     *
     * @param object Any object to convert to JSON
     * @return String representing JSON formatted object
     */
    public <E>String convertToJson(E object){
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();

        return gson.toJson(object);
    }

    /**
     *
     * Converts a given JSON string into an object.
     *
     * @param jsonInfo String representation of JSON
     * @param type     "SomeObject".class
     * @return Object of type E from JSON.
     */
    public <E> E convertFromJson(String jsonInfo,Type type){
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();

        return gson.fromJson(jsonInfo,type);
    }

}
