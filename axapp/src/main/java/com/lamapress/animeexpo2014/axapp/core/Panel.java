package com.lamapress.animeexpo2014.axapp.core;

import java.util.GregorianCalendar;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class Panel {

    String m_sPanelDescription;
    String m_sPanelType;
    String m_sPanelRoom;
    GregorianCalendar m_PanelTime;

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
}
