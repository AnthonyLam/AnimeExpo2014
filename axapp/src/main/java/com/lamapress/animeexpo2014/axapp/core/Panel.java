package com.lamapress.animeexpo2014.axapp.core;

import com.google.gson.annotations.SerializedName;

import java.util.GregorianCalendar;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class Panel {

    @SerializedName("description")
    String m_sPanelDescription;
    @SerializedName("type")
    String m_sPanelType;
    @SerializedName("room")
    String m_sPanelRoom;
    @SerializedName("date")
    public GregorianCalendar m_PanelTime;

    public Panel(){

    }

    public Panel(String description,String type,String room){
        this.m_sPanelDescription = description;
        this.m_sPanelType = type;
        this.m_sPanelRoom = room;
    }

    public void setPanelDay(int year,int month,int day,int hour,int minute){
       m_PanelTime = new GregorianCalendar(year,month,day,hour,minute);
    }

    /*
     * Get methods
     *
     */
    public String getPanelDescription(){
        return m_sPanelDescription;
    }

    public String getPanelType() {
        return m_sPanelType;
    }

    public String getPanelRoom() {
        return m_sPanelRoom;
    }
}
