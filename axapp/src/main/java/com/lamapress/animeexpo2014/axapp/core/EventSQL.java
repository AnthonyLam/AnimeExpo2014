package com.lamapress.animeexpo2014.axapp.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lamapress.animeexpo2014.axapp.network.SQLHelper;

import java.util.ArrayList;

/**
 * Created by Anthony on 6/9/2014.
 */
public class EventSQL {
     public static final String DATABASE_NAME = "convention";
    public static final String DATABASE_TABLE = "event";

    public static final String COL_DESCRIPTION = "description";
    public static final String COL_TYPE = "type";
    public static final String COL_ROOM = "room";
    public static final String COL_DATE = "date";

    public static final String TEXT = "text not null, ";
    public static final String INTEGER = "integer not null";

    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table " + DATABASE_TABLE +
                    "(" + SQLHelper.KEY_ID + " integer primary key autoincrement, " +
                    COL_DESCRIPTION + TEXT +
                    COL_TYPE + TEXT +
                    COL_ROOM + TEXT +
                    COL_DATE + " integer not null);";


    public void saveEvent(Context context,Event event){
        SQLHelper helper = SQLHelper.getInstance(context,DATABASE_NAME,TABLE_CREATE,DATABASE_TABLE,DATABASE_VERSION);

        if(helper != null){
            ContentValues values = new ContentValues();
            values.put(COL_DESCRIPTION,event.getPanelDescription());
            values.put(COL_TYPE,event.getPanelType());
            values.put(COL_ROOM,event.getPanelRoom());
            values.put(COL_DATE,event.m_PanelTime.getTimeInMillis());
            helper.insert(DATABASE_TABLE, values);
        }
    }

    public ArrayList<Event> getEvent(Context context){
        SQLHelper helper =SQLHelper.getInstance(context,DATABASE_NAME,TABLE_CREATE,DATABASE_TABLE,DATABASE_VERSION);
        ArrayList<Event> eventList = new ArrayList<Event>();
        Cursor cursor;

        String[] columns = new String[]{
                SQLHelper.KEY_ID, COL_DESCRIPTION, COL_TYPE, COL_ROOM, COL_DATE
        };


        if(helper != null){
            cursor = helper.get(DATABASE_TABLE,columns);
            ArrayList<Integer> columnId = new ArrayList<Integer>();
            for(int i = 0; i < columns.length; i++){
                columnId.add(cursor.getColumnIndex(columns[i]));
            }

            if(cursor != null && cursor.moveToFirst()){
                    int count = cursor.getCount();

                    for(int i=0; i < count; i++){
                        Event e = new Event(
                                cursor.getString(columnId.get(1)),
                                cursor.getString(columnId.get(2)),
                                cursor.getString(columnId.get(3))
                        );
                        e.m_PanelTime.setTimeInMillis(columnId.get(4));
                        eventList.add(e);
                        cursor.moveToNext();
                    }
                }
            }
        return eventList;
        }
}
