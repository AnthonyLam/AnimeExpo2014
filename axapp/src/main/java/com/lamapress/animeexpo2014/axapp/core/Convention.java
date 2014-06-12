package com.lamapress.animeexpo2014.axapp.core;


import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author Anthony Lam
 * @version 1.2
 *
 * Convention specific information.
 */
@DatabaseTable(tableName = "convention")
public class Convention {

    @DatabaseField(id = true)
    @SerializedName("name")
    public String m_sConventionName = "";
    @DatabaseField
    @SerializedName("center")
    public String m_sConventionCenter = "";

    @DatabaseField
    @SerializedName("daybegin")
    public long m_ConventionDayBegin;
    @DatabaseField
    @SerializedName("dayend")
    public long m_ConventionDayEnd;
    @DatabaseField
    @SerializedName("latitude")
    public double m_dConventionLatitude;
    @DatabaseField
    @SerializedName("longitude")
    public double m_dConventionLongitude;

    /**
     * Required public constructor for ORMLite
     */
    public Convention(){}

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
        GregorianCalendar begin = new GregorianCalendar(year,month,dayBegin);
        GregorianCalendar end = new GregorianCalendar(year,month,dayEnd);
        m_ConventionDayBegin = (begin.getTimeInMillis());
        m_ConventionDayEnd = (end.getTimeInMillis());
    }

    public interface ConventionService{
        @GET("/convention-collection")
        void listItems( Callback<List<Convention>> cb);
    }
}
