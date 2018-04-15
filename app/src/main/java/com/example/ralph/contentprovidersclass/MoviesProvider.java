package com.example.ralph.contentprovidersclass;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MoviesProvider extends ContentProvider {


    public static final int CODE_MOVIES = 100;
    public static final int CODE_MOVIE_ITEM = 101;
    public static final int CODE_REVIEWS = 200;

    public MoviesOpenHelper openHelper;

    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(MoviesContract.AUTHORITY,MoviesContract.PATH_MOVIES,CODE_MOVIES);
        uriMatcher.addURI(MoviesContract.AUTHORITY,MoviesContract.PATH_MOVIES + "/#",CODE_MOVIE_ITEM);
        uriMatcher.addURI(MoviesContract.AUTHORITY,MoviesContract.PATH_REVIEWS,CODE_REVIEWS);

    }

    public MoviesProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        switch (match){
            case CODE_MOVIES:
                return openHelper.getWritableDatabase().delete(MoviesContract.Movies.TABLE_NAME,selection,selectionArgs);

            case CODE_MOVIE_ITEM:
                String id = uri.getLastPathSegment();
                return openHelper.getWritableDatabase().delete(MoviesContract.Movies.TABLE_NAME,MoviesContract.Movies._ID + " = ?",new String[]{id});

            case  CODE_REVIEWS:
                return openHelper.getWritableDatabase().delete(MoviesContract.Reviews.TABLE_NAME,selection,selectionArgs);

        }
        return  -1;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case CODE_MOVIES:
                return MoviesContract.Movies.CONTENT_TYPE;

            case CODE_MOVIE_ITEM:
                return  MoviesContract.Movies.CONTENT_ITEM_TYPE;

            case  CODE_REVIEWS:
                return MoviesContract.Reviews.CONTENT_TYPE;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri output = null;
        int match = uriMatcher.match(uri);
        switch (match){
            case CODE_MOVIES:
                long id =  openHelper.getWritableDatabase().insert(MoviesContract.Movies.TABLE_NAME,null,values);
                output = MoviesContract.Movies.buildMovieUri(id);
            case  CODE_REVIEWS:
                long rid =  openHelper.getWritableDatabase().insert(MoviesContract.Reviews.TABLE_NAME,null,values);
                output = MoviesContract.Reviews.buildReviewUri(rid);
        }
        return  output;
    }

    @Override
    public boolean onCreate() {
        openHelper = MoviesOpenHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor output = null;
        int match = uriMatcher.match(uri);
        switch (match){
            case CODE_MOVIES:
                output = openHelper.getReadableDatabase().query(MoviesContract.Movies.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CODE_MOVIE_ITEM:
                String id = uri.getLastPathSegment();
                output = openHelper.getReadableDatabase().query(MoviesContract.Movies.TABLE_NAME,projection,MoviesContract.Movies._ID + " = ?",new String[]{id},null,null,sortOrder);
                break;
            case  CODE_REVIEWS:
                output = openHelper.getReadableDatabase().query(MoviesContract.Reviews.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
        }
        return output;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        switch (match){
            case CODE_MOVIES:
                return openHelper.getWritableDatabase().update(MoviesContract.Movies.TABLE_NAME,values,selection,selectionArgs);

            case CODE_MOVIE_ITEM:
                String id = uri.getLastPathSegment();
                return openHelper.getWritableDatabase().update(MoviesContract.Movies.TABLE_NAME,values,MoviesContract.Movies._ID + " = ?",new String[]{id});

            case  CODE_REVIEWS:
                return openHelper.getWritableDatabase().update(MoviesContract.Reviews.TABLE_NAME,values,selection,selectionArgs);

        }
        return  -1;
    }
}
