package com.example.ralph.contentprovidersclass;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(adapter);
        //fetchContacts();
        //fetchMovies();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_PICK);
//                intent.setType(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);
//                startActivityForResult(intent,1);

                Movie movie = new Movie("abc");
                ContentValues contentValues = new ContentValues();
                contentValues.put(MoviesContract.Movies.TITLE,movie.getTitle());
                getContentResolver().insert(MoviesContract.Movies.CONTENT_URI,contentValues);
                //fetchMovies();

            }
        });

        getLoaderManager().initLoader(1,null,this);
    }

    private void fetchMovies() {
        Uri contactsUri = MoviesContract.Movies.CONTENT_URI;
        Cursor cursor = getContentResolver().query(contactsUri,null,null,null,null);
        if(cursor != null){
            contacts.clear();
            while (cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndex(MoviesContract.Movies.TITLE));
                contacts.add(title);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Uri contactUri = data.getData();
            Cursor cursor =getContentResolver().query(contactUri,null,null,null,null);
            if(cursor != null  && cursor.moveToFirst()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME));
                String email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));

                Toast.makeText(this,email,Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fetchContacts() {
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = getContentResolver().query(contactsUri,null,null,null,null);
        if(cursor != null){
            contacts.clear();
            while (cursor.moveToNext()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contacts.add(displayName);
            }
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(this,MoviesContract.Movies.CONTENT_URI,null,null,null,null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor != null){
            contacts.clear();
            while (cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndex(MoviesContract.Movies.TITLE));
                contacts.add(title);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
