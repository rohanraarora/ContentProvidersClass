package com.example.ralph.contentprovidersclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ralph on 15/04/18.
 */

public class MoviesOpenHelper extends SQLiteOpenHelper {


    private static MoviesOpenHelper INSTANCE;

    public static MoviesOpenHelper getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new MoviesOpenHelper(context);
        }
        return INSTANCE;
    }

    private MoviesOpenHelper(Context context) {
        super(context.getApplicationContext(), "movies_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + MoviesContract.Movies.TABLE_NAME + " ( " +
                MoviesContract.Movies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MoviesContract.Movies.TITLE + " TEXT)";

        String rSql = "CREATE TABLE " + MoviesContract.Reviews.TABLE_NAME + " ( " +
                MoviesContract.Reviews._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MoviesContract.Reviews.REVIEW + " TEXT, " +
                MoviesContract.Reviews.MOVIE_ID + " INTEGER)";

        db.execSQL(sql);
        db.execSQL(rSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
