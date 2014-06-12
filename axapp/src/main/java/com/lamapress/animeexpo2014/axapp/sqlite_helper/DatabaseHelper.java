package com.lamapress.animeexpo2014.axapp.sqlite_helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.lamapress.animeexpo2014.axapp.R;
import com.lamapress.animeexpo2014.axapp.core.Convention;
import com.lamapress.animeexpo2014.axapp.core.Event;
import com.lamapress.animeexpo2014.axapp.core.Guest;
import com.lamapress.animeexpo2014.axapp.core.Panel;
import com.lamapress.animeexpo2014.axapp.core.Room;

import java.sql.SQLException;

/**
 * Created by Anthony on 6/12/2014.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{
    public static final String DATABASE_NAME = "conventiondata.db";
    public static final int DATABASE_VERSION = 1;

    private Dao<Convention, String> ConventionDao;
    private Dao<Event,String> EventDao;
    private Dao<Guest,String> GuestDao;
    private Dao<Panel,String> PanelDao;
    private Dao<Room,String> RoomDao;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase db,ConnectionSource cs){
           try{
               TableUtils.createTable(cs, Convention.class);
               TableUtils.createTable(cs,Event.class);
               TableUtils.createTable(cs,Guest.class);
               TableUtils.createTable(cs,Panel.class);
               TableUtils.createTable(cs,Room.class);
           }
           catch(SQLException e){
               Log.e(DatabaseHelper.class.getName(), "Unalbe to drop databases", e);
           }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVer,int newVer){
        try {
            TableUtils.dropTable(cs, Convention.class,true);
            TableUtils.dropTable(cs, Event.class,true);
            TableUtils.dropTable(cs, Guest.class,true);
            TableUtils.dropTable(cs, Panel.class,true);
            TableUtils.dropTable(cs, Room.class,true);
            onCreate(db,cs);
        }
        catch(SQLException e){
            Log.e(DatabaseHelper.class.getName(),"Unable to upgrade db from version " + oldVer + " to new " +
                newVer,e);
        }
    }

    public Dao<Convention,String> getConventionDao() throws SQLException {
        if(ConventionDao == null){
            ConventionDao = getDao(Convention.class);
        }
        return ConventionDao;
    }

    public Dao<Event,String> getEventDao() throws SQLException {
        if(EventDao == null){
            EventDao = getDao(Event.class);
        }
        return EventDao;
    }

    public Dao<Guest,String> getGuestDao() throws SQLException {
        if(GuestDao == null){
            GuestDao = getDao(Guest.class);
        }
        return GuestDao;
    }

    public Dao<Panel,String> getPanelDao() throws SQLException {
        if(PanelDao == null){
            PanelDao = getDao(Panel.class);
        }
        return PanelDao;
    }

    public Dao<Room,String> getRoomDao() throws SQLException {
        if(RoomDao == null){
            RoomDao = getDao(Room.class);
        }
        return RoomDao;
    }
}
