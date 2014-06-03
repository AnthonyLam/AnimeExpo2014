package com.lamapress.animeexpo2014.axapp.core;

import java.util.GregorianCalendar;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class Event {

    String m_sEventDescription;
    String m_sEventType;
    String m_sEventRoom;
    GregorianCalendar m_EventTime;

    public Event(){

    }

    public Event(String description,String type,String room){
        this.m_sEventDescription = description;
        this.m_sEventType = type;
        this.m_sEventRoom = room;
    }

    public void setEventDay(int year,int month,int day,int hour,int minute){
       m_EventTime = new GregorianCalendar(year,month,day,hour,minute);
    }
}
