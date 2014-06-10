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

    @SerializedName("name")
    public final String m_sConventionName;
    @SerializedName("center")
    public final String m_sConventionCenter;

    @SerializedName("daybegin")
    public GregorianCalendar m_ConventionDayBegin;
    @SerializedName("dayend")
    public GregorianCalendar m_ConventionDayEnd;
    @SerializedName("latitude")
    double m_dConventionLatitude;
    @SerializedName("longitude")
    double m_dConventionLongitude;


    /**
     * @param conventionName    Name of specific convention
     * @param conventionCenter  Name of convention center
     * @param latitude          Latitude position of convention center
     * @param longitude         Longitude position of convention center
     */
    public Convention(String conventionName,String conventionCenter,double latitude,double longitude){
        m_sConventionName = conventionName;
        m_sConventionCenter = conventionCenter;
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
