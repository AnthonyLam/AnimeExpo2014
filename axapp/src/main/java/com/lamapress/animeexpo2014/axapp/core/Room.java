package com.lamapress.animeexpo2014.axapp.core;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class Room {

    String m_sRoomName;
    String m_sRoomDescription;
    double m_dRoomLongitude;
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
