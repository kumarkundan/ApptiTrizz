package com.demoapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pooja negi on 8/6/2016.
 *
 *
 */

public class MainActivity extends ListActivity {

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    DatabaseHandler db;

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://api.androidhive.info/contacts/";


    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DatabaseHandler(this);

        contactList = new ArrayList<HashMap<String, String>>();


        // Calling async task to get json
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */

    class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            // Reading all contacts
            Log.d("Reading: ", "Reading all contacts..");
            Cursor c = db.getAllContacts();

              /*  for (Contact cn : contacts) {
                    String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                    // Writing Contacts to log
                    Log.d("Name: ", log);

                }*/



            c.moveToFirst();


            while(c.moveToNext()){
                HashMap<String,String> temp = new HashMap<String,String>();
                Log.d("Debug", c.getString(2));
                temp.put("AAA", c.getString(1));
                temp.put("BBB", c.getString(2));
                //  temp.put("CCC", c.getString(3));
                list.add(temp);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, list,
                    R.layout.layout, new String[] {"AAA", "BBB" }, new int[] { R.id.namefill,
                    R.id.unfill });

            setListAdapter(adapter);
        }

    }




}
