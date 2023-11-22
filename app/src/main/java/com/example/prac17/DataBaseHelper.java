package com.example.prac17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "manga.db";
    public static final int SCHEMA = 1;
    static final String TABLE_NAME = "manga";
    public static final String COLUMN_ID = "id_manga";
    public static final String COLUMN_NAME = "manga_name";
    public static final String COLUMN_AUTHOR = "manga_author";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" (" + COLUMN_ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
        + " TEXT, " + COLUMN_AUTHOR + " INTEGER);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void addManga(Group group) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, group.getManga_Name());
        contentValues.put(COLUMN_AUTHOR, group.getManga_Author());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }
    public ArrayList<Group> getMangaList() {
        ArrayList<Group> listManga = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if (result.moveToFirst()) {
            while (result.moveToNext()) {
                int id = result.getInt(0);
                String mangaName = result.getString(1);
                String mangaAuthor = result.getString(2);
                Group group = new Group(id, mangaName, mangaAuthor);
                listManga.add(group);
            }
        }
        result.close();
        return listManga;
    }

    public Group getManga(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_AUTHOR},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            Group group = new Group(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            cursor.close();
            return group;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public void deleteManga(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}
