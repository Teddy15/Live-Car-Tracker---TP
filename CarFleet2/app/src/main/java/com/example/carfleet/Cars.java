package com.example.carfleet;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;

import java.util.Date;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Cars extends SQLiteOpenHelper {

    public Cars(Context context){
        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS actors(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name TEXT UNIQUE NOT NULL," +
                "age INTEGER NOT NULL" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(" DROP TABLE IF EXISTS actors;");
            onCreate(db);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
        super.onOpen(db);
    }


    public boolean add(Car car){
        if (!exists(car)) {
            SQLiteDatabase db = this.getWritableDatabase();
            SQLiteStatement stmt = db.compileStatement("INSERT INTO movies(name) " +
                    "VALUES(?);");
            int id;

            stmt.bindString(1, car.getName());
//            stmt.bindString(2, car.getReleaseDate().toString());
            id = (int) stmt.executeInsert();
            if (id != -1) {
                car.setId(id);
                return true;
            }
        }
        return false;
    }
    public Car findById(int id) {
        String[] cols = new String[]{ String.valueOf(id) };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cars " +
                "WHERE ID = ?;", cols);
        Car car = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String name = cursor.getString(1);
                Date release_date = new Date(cursor.getString(2));

                car = new Car(id, name, release_date);
            }
            cursor.close();
        }
        return car;
    }

    public Car findByName(String name) {
        String[] cols = new String[]{ name };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cars " +
                "WHERE name = ?;", cols);
        Car car = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(0);
                Date release_date = new Date(cursor.getString(2));
                car = new Car(id, name, release_date);
            }
            cursor.close();
        }
        return car;
    }

    private boolean exists(Car car) {
        return findById(car.getId()) != null || findByName(car.getName()) != null;
    }
}
