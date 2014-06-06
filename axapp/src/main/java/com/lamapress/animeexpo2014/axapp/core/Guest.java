package com.lamapress.animeexpo2014.axapp.core;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class Guest {

    // List of possible guest types
    public enum GuestType {
        PEOPLE,
        GROUP,
        COMPANY
    }

    @SerializedName("about")
    String m_sAbout;
    @SerializedName("website-url")
    String m_websiteURL;
    @SerializedName("wiki-url")
    String m_wikiURL;
    @SerializedName("image-url")
    String m_sImageBitmap;
    GuestType type;

    /**
     *
     * @param website Guest's website URL
     * @param wiki    Guest's wiki page if it exists
     * @param bitmap  Bitmap name for a previously downloaded image
     * @param type    PEOPLE,GROUP or COMPANY
     */
    public Guest(String website,String wiki,String bitmap,GuestType type){
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

    public String getM_sAbout() {
        return m_sAbout;
    }

    public void setM_sAbout(String m_sAbout) {
        this.m_sAbout = m_sAbout;
    }

    public URL getwebsiteURL() {
        URL url;
        try {
            url = new URL(m_websiteURL);
            return url;
        }
        catch(MalformedURLException e){
            return null;
        }
    }

    public URL getwikiURL() {
        URL url;
        try{
            url = new URL(m_wikiURL);
            return url;
        }
        catch(MalformedURLException e){
            return null;
        }
    }


    public String getM_sImageBitmap() {
        return m_sImageBitmap;
    }

    public void setM_sImageBitmap(String m_sImageBitmap) {
        this.m_sImageBitmap = m_sImageBitmap;
    }

}
