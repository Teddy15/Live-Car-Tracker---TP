package com.example.carfleet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import org.w3c.dom.Text;

import java.util.List;

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
        db.execSQL("CREATE TABLE createGroup (ID INTEGER PRIMARY KEY AUTOINCREMENT, name Text, users Text, manager_name Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

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
        Cursor cursor = db.rawQuery("SELECT * from createGroup", null);
        return cursor;
    }
}
