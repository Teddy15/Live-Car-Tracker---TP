package com.example.carfleet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateGroup extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "create_group.db";
    public static final String TABLE_NAME = "createGroup";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "name";
    public static final String COL_3 = "users";
    public static final String COL_4 = "manager_name";

    public CreateGroup(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE createGroup (ID INTEGER PRIMARY KEY AUTOINCREMENT, name Text, users Text, manager_name Text, COL_5 Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
