package com.lamapress.animeexpo2014.axapp.core;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class Guest {

    // List of possible guest types
    enum GuestType {
        PEOPLE,
        GROUP,
        COMPANY
    }

    String m_sAbout;
    URL m_websiteURL;
    URL m_wikiURL;
    String m_sImageBitmap;
    GuestType type;

    /**
     *
     * @param website Guest's website URL
     * @param wiki    Guest's wiki page if it exists
     * @param bitmap  Bitmap name for a previously downloaded image
     * @param type    PEOPLE,GROUP or COMPANY
     */
    public Guest(URL website,URL wiki,String bitmap,GuestType type){
        this.m_websiteURL = website;
        this.m_wikiURL = wiki;
        this.m_sImageBitmap = bitmap;
        this.type = type;
    }

    /**
     * Sets the about to wiki fragment
     */
    public void setAbout(){
       // TODO: SET TO WIKI FRAGMENT
    }

    /**
     * Sets a custom about paragraph
     * @param about Listing about guest
     */
    public void setAbout(String about){
        this.m_sAbout = about;
    }

}
