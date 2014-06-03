package com.lamapress.animeexpo2014.axapp.core;


/**
 * @author Anthony Lam
 * @version 1.0
 */
public class Panel extends Event {

    public Panel(String description,String type, String room){
        m_sEventDescription = description;
        m_sEventType = type;
        m_sEventRoom = room;
    }
}
