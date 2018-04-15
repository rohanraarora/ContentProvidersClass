package com.example.ralph.contentprovidersclass;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ralph on 15/04/18.
 */

public class MoviesContract {

    public static final String AUTHORITY = "com.example.ralph.contentprovidersclass.provider";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_MOVIES = "movies";
    public static final String PATH_REVIEWS = "reviews";

    public static class Movies implements BaseColumns {

        public static final String TABLE_NAME = "movies";

        public static final String TITLE = "title";

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static Uri buildMovieUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "." + PATH_MOVIES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "." + PATH_MOVIES;
    }

    public static class Reviews implements BaseColumns {

        public static final String TABLE_NAME = "reviews";

        public static final String REVIEW = "review";

        public static final String MOVIE_ID = "movie_id";

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_REVIEWS).build();

        public static Uri buildReviewUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "." + PATH_REVIEWS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "." + PATH_REVIEWS;


    }

}
