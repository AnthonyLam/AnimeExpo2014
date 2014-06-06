package com.lamapress.animeexpo2014.axapp.core;


import com.google.gson.annotations.SerializedName;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class Event extends Panel {

    @SerializedName("cost")
    double m_dEventCost = 0.0;

    public Event(String description,String type, String room){
        m_sPanelDescription = description;
        m_sPanelType = type;
        m_sPanelRoom = room;
    }

    public void setCost(double cost){
        m_dEventCost = cost;
    }

    public double getCost(){
        return m_dEventCost;
    }
}
