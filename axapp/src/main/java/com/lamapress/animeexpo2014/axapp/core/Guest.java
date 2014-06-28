package com.lamapress.animeexpo2014.axapp.core;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author Anthony Lam
 * @version 1.0
 */
@DatabaseTable(tableName = "guest")
public class Guest {

    // List of possible guest types
    @DatabaseField(id=true, useGetSet = true)
    @SerializedName("name")
    String m_sName;

    @DatabaseField(useGetSet = true)
    @SerializedName("about")
    String m_sAbout;

    @DatabaseField(useGetSet = true)
    @SerializedName("websiteurl")
    String m_websiteURL;

    @DatabaseField(useGetSet = true)
    @SerializedName("wikiurl")
    String m_wikiURL;

    @DatabaseField(useGetSet = true)
    @SerializedName("imageurl")
    String m_sImageBitmap;

    @DatabaseField
    String type;

    public Guest(){}

    /**
     *
     * @param website Guest's website URL
     * @param wiki    Guest's wiki page if it exists
     * @param bitmap  Bitmap name for a previously downloaded image
     * @param type    PEOPLE,GROUP or COMPANY
     */
    public Guest(String name,String website,String wiki,String bitmap,String type){
        this.m_sName = name;
        this.m_websiteURL = website;
        this.m_wikiURL = wiki;
        this.m_sImageBitmap = bitmap;
        this.type = type;
    }

    public interface GuestService{
        @GET("/guest-collection")
        void listItems(Callback<List<Guest>> cb);
    }

    public String getM_sName() {
        return m_sName;
    }

    public void setM_sName(String m_sName) {
        this.m_sName = m_sName;
    }

    public String getM_sAbout() {
        return m_sAbout;
    }

    public void setM_sAbout(String m_sAbout) {
        this.m_sAbout = m_sAbout;
    }

    public String getM_websiteURL() {
        return m_websiteURL;
    }

    public void setM_websiteURL(String m_websiteURL) {
        this.m_websiteURL = m_websiteURL;
    }

    public String getM_wikiURL() {
        return m_wikiURL;
    }

    public void setM_wikiURL(String m_wikiURL) {
        this.m_wikiURL = m_wikiURL;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    /**
     * Sets the about to wiki fragment
     */

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
