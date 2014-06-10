package com.lamapress.animeexpo2014.axapp.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lamapress.animeexpo2014.axapp.network.SQLHelper;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Anthony on 6/9/2014.
 */
public class ConventionSQL {
    public static final String DATABASE_NAME = "convention";
    public static final String DATABASE_TABLE = "convention_info";

    public static final String COL_NAME = "name";
    public static final String COL_CENTER = "center";
    public static final String COL_DAYBEGIN = "daybegin";
    public static final String COL_DAYEND = "dayend";
    public static final String COL_LATITUDE = "latitude";
    public static final String COL_LONGITUDE = "longitude";

    public static final String TEXT = "text not null, ";
    public static final String INTEGER = "integer not null";

    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table " + DATABASE_TABLE +
                    "(" + SQLHelper.KEY_ID + " integer primary key autoincrement, " +
                    COL_NAME + TEXT +
                    COL_CENTER + TEXT +
                    COL_DAYBEGIN + INTEGER +
                    COL_DAYEND + INTEGER +
                    COL_LATITUDE + TEXT +
                    COL_LONGITUDE + " text not null);";


    public void saveConvention(Context context,Convention convention){
        SQLHelper helper = SQLHelper.getInstance(context,DATABASE_NAME,TABLE_CREATE,DATABASE_TABLE,DATABASE_VERSION);

        if(helper != null){
            ContentValues values = new ContentValues();
            values.put(COL_NAME,convention.m_sConventionName);
            values.put(COL_CENTER,convention.m_sConventionCenter);
            values.put(COL_DAYBEGIN,convention.m_ConventionDayBegin.getTimeInMillis());
            values.put(COL_DAYEND,convention.m_ConventionDayEnd.getTimeInMillis());
            values.put(COL_LATITUDE,convention.m_dConventionLatitude);
            values.put(COL_LONGITUDE,convention.m_dConventionLongitude);
            helper.insert(DATABASE_TABLE, values);
        }
    }

    public ArrayList<Convention> getConvention(Context context){
        SQLHelper helper =SQLHelper.getInstance(context,DATABASE_NAME,TABLE_CREATE,DATABASE_TABLE,DATABASE_VERSION);
        ArrayList<Convention> conventionList = new ArrayList<Convention>();
        Cursor cursor;

        String[] columns = new String[]{
            SQLHelper.KEY_ID, COL_NAME, COL_CENTER, COL_DAYBEGIN, COL_DAYEND, COL_LATITUDE, COL_LONGITUDE
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
                        Convention c = new Convention(
                                cursor.getString(columnId.get(1)), // COL_NAME
                                cursor.getString(columnId.get(2)), // COL_CENTER
                                cursor.getDouble(columnId.get(5)), // COL_LATITUDE
                                cursor.getDouble(columnId.get(6))  // COL_LONGITUDE
                        );
                        c.m_ConventionDayBegin.setTimeInMillis(columnId.get(3)); // COL_DAYBEGIN
                        c.m_ConventionDayEnd.setTimeInMillis(columnId.get(4));   // COL_DAYEND

                        conventionList.add(c);
                        cursor.moveToNext();
                    }
                }
            }
        return conventionList;
        }
}
