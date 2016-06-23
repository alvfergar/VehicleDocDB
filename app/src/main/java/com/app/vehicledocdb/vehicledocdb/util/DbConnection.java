package com.app.vehicledocdb.vehicledocdb.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.vehicledocdb.vehicledocdb.greendaomodel.DaoMaster;
import com.app.vehicledocdb.vehicledocdb.greendaomodel.DaoSession;

/**
 * Created by Alvaro on 07/06/2016.
 */
public class DbConnection {

    public static DaoMaster daoMaster;
    private static SQLiteDatabase db;

    public static DaoSession getDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "vehicle-db", null);
        if (db == null) {
            db = helper.getWritableDatabase();
            Log.wtf("Database: ", "db == null");
        } else {
            if (!db.isOpen()) {
                db = helper.getWritableDatabase();
                Log.wtf("Database ", "db != null && db != isOpen()");
            } else {
                Log.wtf("Database ", "db != null && db is open");
            }
        }

        daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public static void closeDb() {
        if (db != null) {
            if (db.isOpen()) {
                db.close();
            }
        }
    }
}
