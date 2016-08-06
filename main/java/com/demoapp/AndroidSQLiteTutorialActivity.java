package com.demoapp;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AndroidSQLiteTutorialActivity extends ListActivity {

    EditText name,mobile;
    Button insert,fetch;
    TableLayout tableExtra;
    private ProgressDialog pDialog;
   // ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    DatabaseHandler db;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        db = new DatabaseHandler(this);

        name=(EditText)findViewById(R.id.ename);
        mobile=(EditText)findViewById(R.id.emobile);
        insert=(Button)findViewById(R.id.button);
        fetch=(Button)findViewById(R.id.button2);

       // tableExtra=(TableLayout)findViewById(R.id.table);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * CRUD Operations
                 * */
                // Inserting Contacts
                Log.d("Insert: ", "Inserting ..");
                db.addContact(new Contact(name.getText().toString(), mobile.getText().toString()));
                name.setText("");
                mobile.setText("");
                Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_SHORT).show();

            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(AndroidSQLiteTutorialActivity.this,MainActivity.class);
                startActivity(in);

            }
        });


        


    }


}