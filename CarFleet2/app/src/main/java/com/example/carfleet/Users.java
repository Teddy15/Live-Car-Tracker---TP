package com.example.carfleet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Users extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "users";

    public Users(Context context) {
        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "username TEXT UNIQUE NOT NULL," +
                "password TEXT NOT NULL" +
                ");");
    }

    //@Override
    //public void onOpen(SQLiteDatabase db) {
    //    onCreate(db);
    //    super.onOpen(db);
    // }

    /*@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(" DROP TABLE IF EXISTS users;");
            onCreate(db);
        }
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean add(User user) {
        if(!exists(user)) {
            SQLiteDatabase db = getWritableDatabase();
            SQLiteStatement stmt = db.compileStatement("INSERT INTO users(username, password) " + "VALUES(?, ?);");
            int id;
            stmt.bindString(1, user.getEmail());
            stmt.bindString(2, user.getPassword());

            id = (int) stmt.executeInsert();
            if(id != -1) {
                user.setId(id);
                return true;
            }
        }
        return false;
    }

    public User findByEmail(String username) {
        String[] cols = new String[]{ username };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM users " +
                "WHERE username=?;", cols);
        User user = null;

        if (mCursor != null){
            if (mCursor.moveToFirst()) {
                int id = mCursor.getInt(0);
                user = new User(id, username);
            }
            mCursor.close();
        }
        return user;
    }

    public boolean exists(User user) {
        return findByEmail(user.getEmail()) != null;
    }
}