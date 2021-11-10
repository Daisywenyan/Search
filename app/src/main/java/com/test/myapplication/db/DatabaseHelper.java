package com.test.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "user.db";
    static int dbVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, name, null, dbVersion);
    }


    public void onCreate(SQLiteDatabase db) {
        String sql = "create table chat(id integer primary key autoincrement,message varchar,type varchar(20))";
        db.execSQL(sql);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

