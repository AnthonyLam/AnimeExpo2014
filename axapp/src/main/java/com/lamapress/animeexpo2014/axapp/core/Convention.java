package com.lamapress.animeexpo2014.axapp.core;


import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.util.GregorianCalendar;

/**
 * @author Anthony Lam
 * @version 1.1
 *
 * Convention specific information.
 */
public class Convention {

    @SerializedName("convention_name")
    String s_sConventionName;
    @SerializedName("convention_center")
    String s_sConventionCenter;

    @SerializedName("convention_day_begin")
    public GregorianCalendar m_ConventionDayBegin;
    @SerializedName("convention_day_end")
    public GregorianCalendar m_ConventionDayEnd;
    @SerializedName("convention_latitude")
    double m_dConventionLatitude;
    @SerializedName("convention_longitude")
    double m_dConventionLongitude;


    /**
     * @param conventionName    Name of specific convention
     * @param conventionCenter  Name of convention center
     * @param latitude          Latitude position of convention center
     * @param longitude         Longitude position of convention center
     */
    public Convention(String conventionName,String conventionCenter,double latitude,double longitude){
        s_sConventionName = conventionName;
        s_sConventionCenter = conventionCenter;
        this.m_dConventionLatitude = latitude;
        this.m_dConventionLongitude = longitude;
    }

    /**
     * Gets a Google Maps location
     * @return Returns LatLng object for convention location
     */
    public LatLng getMapsLocation(){
        LatLng location = new LatLng(m_dConventionLatitude,m_dConventionLongitude);
        return location;
    }

    /**
     *
     * @param year
     * @param month
     * @param dayBegin
     * @param dayEnd
     */
    public void setConventionDay(int year,int month,int dayBegin,int dayEnd){
        m_ConventionDayBegin = new GregorianCalendar(year,month,dayBegin);
        m_ConventionDayEnd = new GregorianCalendar(year,month,dayEnd);
    }

}
