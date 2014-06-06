package com.lamapress.animeexpo2014.axapp.core;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class Room {

    @SerializedName("room")
    String m_sRoomName;
    @SerializedName("description")
    String m_sRoomDescription;
    @SerializedName("longitude")
    double m_dRoomLongitude;
    @SerializedName("latitude")
    double m_dRoomLatitude;

    public Room(String name,String description,double latitude,double longitude){
        this.m_sRoomName = name;
        this.m_sRoomDescription = description;
        this.m_dRoomLatitude = latitude;
        this.m_dRoomLongitude = longitude;
    }

    public LatLng getMapsLocation(){
        LatLng location = new LatLng(m_dRoomLatitude,m_dRoomLongitude);
        return location;
    }
}
