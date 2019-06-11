package com.example.carfleet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateGroup extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "groups";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "name";
    public static final String COL_3 = "users";
    public static final String COL_4 = "manager_name";

    public CreateGroup(Context context) {
        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE groups(ID INTEGER PRIMARY KEY AUTOINCREMENT, name Text, users Text, manager_name Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion) {
            db.execSQL(" DROP TABLE IF EXISTS groups;");
            onCreate(db);
        }
    }

    /*@Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
        super.onOpen(db);
    }*/

    public boolean insertData(String name, String users, String manager) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, users);
        contentValues.put(COL_4, manager);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public String getName() {
        return COL_2;
    }

    public Cursor readData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM groups", null);
        return cursor;
    }
}