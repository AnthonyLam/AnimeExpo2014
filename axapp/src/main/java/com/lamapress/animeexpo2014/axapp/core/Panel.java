package com.lamapress.animeexpo2014.axapp.core;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author Anthony Lam
 * @version 1.0
 */
@DatabaseTable(tableName = "panel")
public class Panel {

    @DatabaseField(id=true,useGetSet = true)
    @SerializedName("id")
    String id;

    @DatabaseField(useGetSet = true)
    @SerializedName("name")
    String m_sPanelName;

    @DatabaseField
    @SerializedName("dateBegin")
    public long m_PanelTime;

    @DatabaseField
    @SerializedName("dateEnd")
    public long m_PanelEnd;

    @DatabaseField(useGetSet = true)
    @SerializedName("description")
    String m_sPanelDescription;

    @DatabaseField(useGetSet = true)
    @SerializedName("type")
    String m_sPanelType;

    @DatabaseField(useGetSet = true)
    @SerializedName("room")
    String m_sPanelRoom;

    @DatabaseField(useGetSet = true)
    @SerializedName("favorited")
    Boolean favorited = false;


    public Panel(){}

    public Panel(String name,String description,String type,String room) {
        this.m_sPanelName = name;
        this.m_sPanelDescription = description;
        this.m_sPanelType = type;
        this.m_sPanelRoom = room;
    }

    public void setPanelDay(int year,int month,int day,int hour,int minute){
        GregorianCalendar cal = new GregorianCalendar(year,month,day,hour,minute);
        m_PanelTime = (cal.getTimeInMillis());
    }


    public interface PanelService{
        @GET("/panel-collection")
        void listItems(Callback<List<Panel>> cb);
    }

    /*
     * Get methods
     *
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public String getM_sPanelName() {
        return m_sPanelName;
    }

    public void setM_sPanelName(String m_sPanelName) {
        this.m_sPanelName = m_sPanelName;
    }

    public String getM_sPanelRoom() {
        return m_sPanelRoom;
    }

    public void setM_sPanelRoom(String m_sPanelRoom) {
        this.m_sPanelRoom = m_sPanelRoom;
    }

    public String getM_sPanelDescription() {
        return m_sPanelDescription;
    }

    public void setM_sPanelDescription(String m_sPanelDescription) {
        this.m_sPanelDescription = m_sPanelDescription;
    }

    public String getM_sPanelType() {
        return m_sPanelType;
    }

    public void setM_sPanelType(String m_sPanelType) {
        this.m_sPanelType = m_sPanelType;
    }
}
