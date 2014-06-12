package com.lamapress.animeexpo2014.axapp.network;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lamapress.animeexpo2014.axapp.HomeScreen;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author Anthony Lam
 * @version 1.0
 */
public class SQLHelper extends SQLiteOpenHelper{

    private static int DB_VERSION = 1;
    private static SQLiteDatabase db;
    private static String s_dbName;
    private static String s_tableName;
    private static String s_sqlQuery;
    public static final String KEY_ID ="_id";

    private static SQLHelper instance;

    public SQLHelper(Context context,String dbName,String sql,String tableName,int ver){
        super(context,dbName,null,ver);
        this.s_sqlQuery = sql;
        this.s_tableName = tableName;
        this.s_dbName = dbName;
    }

    public static SQLHelper getInstance(Context context,String dbName,String sql,String tableName, int ver){
        if(instance == null){
            instance = new SQLHelper(context,dbName,sql,tableName,ver);
            try{
                Log.v("FYI","Database " + s_dbName + " opened");
                db = instance.getWritableDatabase();
            }
            catch(SQLiteException e){
                Log.e("SQLHelper", "Could not create and/or open the db", e);
            }
        }
        return instance;
    }

    public void close(){
        if(instance != null){
            db.close();
            instance = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        try{
            Log.v("FYI","Creating database table [ " + s_sqlQuery + " ]");
            db.execSQL(s_sqlQuery);
        }
        catch(SQLiteException e){
            Log.e("FYI","Could not create the database according to the SQL statement [ " + s_sqlQuery + " ]");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        try{
            db.execSQL("DROP TABLE IF EXISTS " + s_tableName);
        }
        catch(SQLiteException e){

        }
        onCreate(db);
    }

    public void dropDB(SQLiteDatabase db){
        try{
            db.execSQL("DROP TABLE IF EXISTS " + s_tableName);
        }
        catch(SQLiteException e){

        }
        onCreate(db);
    }

    public long insert(String table,ContentValues values){
        return db.insert(table,null,values);
    }

    public Cursor get(String table,String[] columns){
        return db.query(table,columns,null,null,null,null,null);
    }

    public Cursor get(String table,String[] columns,long id){
        Cursor cursor = db.query(true,table,columns,KEY_ID + "=" + id,null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int delete(String table){
        return db.delete(table,"1",null);
    }

    public int delete(String table, long id){
        return db.delete(table,KEY_ID + "=" + id,null);
    }

    public int update(String table,long id, ContentValues values){
        return db.update(table,values,KEY_ID +"=" +id,null);
    }

    /*public String makeTableQuery(String tableName,String[] tableData) {

        // Table and ID portion of query
        String query = "create table " + tableName + "("
                + "id integer primary key autoincrement,";

        // Data column queries
        for(int i=0; i < tableData.length - 1; i++){
            query = query + tableData[i] + ",";
        }
        // Don't add a comma to the last column
        query = query +tableData[tableData.length - 1] + ");";
        return query;
    }*/

    public String quoteReplace(String query){
        String regex = "/'/g";
        return query.replaceAll(regex,"''");
    }
}
