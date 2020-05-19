package com.androidapp.flapwings;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper { // 내부 디비 SQLite

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) { //생성자자
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // 디비 생성 (이미 있다면 생성하지 않음) 쿼리문
        db.execSQL("CREATE TABLE IF NOT EXISTS Flap (num INTEGER PRIMARY KEY AUTOINCREMENT,txt_in VARCHAR(50),txt_put VARCHAR(50));");
    }

    public void create_and_load() { // 디비 로딩
        SQLiteDatabase db = getReadableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Insert(String in, String put) { // 단어 추가하는 쿼리문
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Flap VALUES(null,'" + in + "','" + put + "');");
        db.close();

    }

    public String getResult() { // 디비에 있는 모든 결과 값 출력
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT * FROM Flap", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0) + " : " + cursor.getString(1) + " / " + cursor.getString(2) + "\n";
        }
        return result;
    }

    public String getIn() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT * FROM Flap", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(1) + "\n";

        }
        return result;
    }

    public String getPut() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT * FROM Flap", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(2) + "\n";

        }
        return result;
    }
}
