package com.lamapress.animeexpo2014.axapp.core;


import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Anthony Lam
 * @version 1.0
 */
@DatabaseTable(tableName = "event")
public class Event extends Panel {

    @SerializedName("cost")
    @DatabaseField(useGetSet = true)
    double m_dEventCost = 0.0;

    public Event(){}

    public Event(String description,String type, String room){
        m_sPanelDescription = description;
        m_sPanelType = type;
        m_sPanelRoom = room;
    }

    public void setM_dEventCost(double cost){
        m_dEventCost = cost;
    }

    public double getM_dEventCost(){
        return m_dEventCost;
    }
}
