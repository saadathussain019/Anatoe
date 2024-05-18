package com.example.anatoe;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperH extends SQLiteOpenHelper {

    private static final String DATABASE_NAME_H = "Hword_database";
    private static final int DATABASE_VERSION_H = 1;
    public static final String TABLE_NAME_H = "Hword_table";
    public static final String COLUMN_ID_H = "H_id";
    public static final String COLUMN_WORD_H = "Hword";

    private static final String CREATE_TABLE_H =
            "CREATE TABLE " + TABLE_NAME_H + " (" +
                    COLUMN_ID_H + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_WORD_H + " TEXT NOT NULL);";

    public DatabaseHelperH(Context context) {
        super(context, DATABASE_NAME_H, null, DATABASE_VERSION_H);
    }

    public DatabaseHelperH(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_H);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_H);
        onCreate(db);
    }

    public Cursor getWordList() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ID_H, COLUMN_WORD_H};
        return db.query(TABLE_NAME_H, projection, null, null, null, null, null);
    }
}
