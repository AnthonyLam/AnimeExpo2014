package com.lamapress.animeexpo2014.axapp.core;

import com.google.gson.annotations.SerializedName;

import java.util.GregorianCalendar;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 */
public class News {

    @SerializedName("title")
    String m_sNewsTitle;

    @SerializedName("description")
    String m_sNewsDescription;

    @SerializedName("date")
    int m_sNewsDate;

    public News(String title,String description,int date){
        this.m_sNewsTitle = title;
        this.m_sNewsDescription = description;
        this.m_sNewsDate = date;
    }

    public interface NewsServices{
        @GET("/news")
        void listItems(Callback<List<News>> news);
    }

    public GregorianCalendar convertTime(){
        GregorianCalendar cal =new GregorianCalendar();
        cal.setTimeInMillis(m_sNewsDate);
        return cal;
    }

    public String getM_sNewsTitle() {
        return m_sNewsTitle;
    }

    public void setM_sNewsTitle(String m_sNewsTitle) {
        this.m_sNewsTitle = m_sNewsTitle;
    }

    public String getM_sNewsDescription() {
        return m_sNewsDescription;
    }

    public void setM_sNewsDescription(String m_sNewsDescription) {
        this.m_sNewsDescription = m_sNewsDescription;
    }

    public int getM_sNewsDate() {
        return m_sNewsDate;
    }

    public void setM_sNewsDate(int m_sNewsDate) {
        this.m_sNewsDate = m_sNewsDate;
    }
}
