package com.galuhsukma.bismillah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "EyArt";
    public static final String DB_TABLE = "AllPost";
    public static final int DB_VER = 1;

    Context ctx;
    SQLiteDatabase myDB;

    public Database(Context ct){
        super(ct, DB_NAME, null,DB_VER);
        ctx = ct;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+DB_TABLE+" (_id integer primary key autoincrement, image_art blob, title_art text, about_art text, favorite int);";
        db.execSQL(query);
        Log.i("Database", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        String query = "drop table if exists "+DB_TABLE+"";
        db.execSQL(query);
    }

    public Long insertData(byte[] image, String title, String about, int favorite){
        myDB = getWritableDatabase();
        /*myDB.execSQL("insert into "+DB_TABLE+" (image_art, title_art, about_art) values('"+
                image+"','"+title+"','"+about+"');");
        Toast.makeText(ctx, "Saved Successfully", Toast.LENGTH_SHORT).show();*/
        ContentValues values = new ContentValues();
        values.put("image_art", image);
        values.put("title_art", title);
        values.put("about_art", about);
        values.put("favorite", favorite);

        long rowId = myDB.insert(DB_TABLE, null, values);

        return rowId;
    }

    public void editDataEntry(int id, byte[] image, String title, String about){
        ContentValues values = new ContentValues();
        values.put("image_art", image);
        values.put("title_art", title);
        values.put("about_art", about);

        myDB = getReadableDatabase();
        myDB.update(DB_TABLE, values, "_id="+id, null);
    }

}
