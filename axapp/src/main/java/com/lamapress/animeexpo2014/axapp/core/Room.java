package com.lamapress.animeexpo2014.axapp.core;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Anthony Lam
 * @version 1.0
 */
@DatabaseTable(tableName = "room")
public class Room {

    @DatabaseField(id=true,useGetSet = true)
    @SerializedName("room")
    String m_sRoomName;

    @DatabaseField(useGetSet = true)
    @SerializedName("description")
    String m_sRoomDescription;

    @DatabaseField(useGetSet = true)
    @SerializedName("longitude")
    double m_dRoomLongitude;

    @DatabaseField(useGetSet = true)
    @SerializedName("latitude")
    double m_dRoomLatitude;

    public Room(){}

    public Room(String name, String description, double latitude, double longitude) {
        this.m_sRoomName = name;
        this.m_sRoomDescription = description;
        this.m_dRoomLatitude = latitude;
        this.m_dRoomLongitude = longitude;
    }

    public LatLng getMapsLocation() {
        LatLng location = new LatLng(m_dRoomLatitude, m_dRoomLongitude);
        return location;
    }

    public String getM_sRoomName() {
        return m_sRoomName;
    }

    public void setM_sRoomName(String m_sRoomName) {
        this.m_sRoomName = m_sRoomName;
    }

    public String getM_sRoomDescription() {
        return m_sRoomDescription;
    }

    public void setM_sRoomDescription(String m_sRoomDescription) {
        this.m_sRoomDescription = m_sRoomDescription;
    }

    public double getM_dRoomLongitude() {
        return m_dRoomLongitude;
    }

    public void setM_dRoomLongitude(double m_dRoomLongitude) {
        this.m_dRoomLongitude = m_dRoomLongitude;
    }

    public double getM_dRoomLatitude() {
        return m_dRoomLatitude;
    }

    public void setM_dRoomLatitude(double m_dRoomLatitude) {
        this.m_dRoomLatitude = m_dRoomLatitude;
    }
}
