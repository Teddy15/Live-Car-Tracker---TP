package com.example.carfleet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Managers extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "managers";

    public Managers(Context context) {
        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS managers(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "username TEXT UNIQUE NOT NULL," +
                "users TEXT" +
                ");");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(" DROP TABLE IF EXISTS managers;");
            onCreate(db);
        }
    }


    public boolean add(Manager manager) {
        if(!exists(manager)) {
            SQLiteDatabase db = getWritableDatabase();
            SQLiteStatement stmt = db.compileStatement("INSERT INTO managers(username, users) " + "VALUES(?, ?);");
            int id;
            stmt.bindString(1, manager.getUsername());
            StringBuilder stringBuilder  = new StringBuilder();
            for(String user : manager.getUsers()) {
                stringBuilder.append(user);
            }
            stmt.bindString(2, stringBuilder.toString());
            id = (int) stmt.executeInsert();
            if(id != -1) {
                manager.setId(id);
                return true;
            }
        }
        return false;
    }

    public boolean exists(Manager manager) {
        return findByName(manager.getUsername()) != null;
    }

    public Manager findByName(String username) {
        String[] cols = new String[]{ username };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM managers " + "WHERE username=?;", cols);
        Manager manager = null;

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                int id = cursor.getInt(0);
                manager = new Manager(id, username);
            }
            cursor.close();
        }
        return manager;
    }
}
