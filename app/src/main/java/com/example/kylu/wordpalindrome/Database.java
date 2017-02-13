package com.example.kylu.wordpalindrome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
    {
        public static final String DATABASE_NAME = "Words.db";
        public static final String TABLE_NAME = "Words_table";
        public static final String COL_1 = "ID";
        public static final String COL_2 = "WORD";
        public static final String COL_3 = "RESULT";

        public Database(Context context)
        {
            super(context, DATABASE_NAME, null, 1);

        }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " TEXT," + COL_3 +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

        public boolean instertData(String word, String result)
        {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2,word);
            contentValues.put(COL_3,result);
            long insertResult = db.insert(TABLE_NAME,null,contentValues);
            if(insertResult == -1)
            {
                return false;
            }
                else
                    return true;
        }

        public Cursor getListContents ()
        {
            SQLiteDatabase db =this.getWritableDatabase();
            Cursor data = db.rawQuery(" SELECT * FROM " + TABLE_NAME, null);
            return data;
        }
}
