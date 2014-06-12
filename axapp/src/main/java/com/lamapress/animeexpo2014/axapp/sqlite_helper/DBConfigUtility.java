package com.lamapress.animeexpo2014.axapp.sqlite_helper;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Anthony Lam
 */
public class DBConfigUtility extends OrmLiteConfigUtil {
    public static void main(String[] args) throws SQLException,IOException{
        writeConfigFile("ormlite_config.txt");
    }
}
