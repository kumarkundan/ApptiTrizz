package com.example.kundan.sqliteapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class AndroidSQLiteTutorialActivity extends Activity {

    EditText name,mobile;
    Button insert,fetch;
    TableLayout tableExtra;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        name=(EditText)findViewById(R.id.ename);
        mobile=(EditText)findViewById(R.id.emobile);
        insert=(Button)findViewById(R.id.button);
        fetch=(Button)findViewById(R.id.button2);

        tableExtra=(TableLayout)findViewById(R.id.table);

        final DatabaseHandler db = new DatabaseHandler(this);
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

                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                Cursor c = db.getAllContacts();

              /*  for (Contact cn : contacts) {
                    String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                    // Writing Contacts to log
                    Log.d("Name: ", log);

                }*/

                int rows = c.getCount();
                int cols = c.getColumnCount();

                c.moveToFirst();

                // outer for loop
                for (int i = 0; i < rows; i++) {

                    TableRow row = new TableRow(getApplicationContext());
                    row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    Log.v("ME RE 33333", String.valueOf(c.getCount()));

                    // inner for loop
                    for (int j = 0; j < cols; j++) {

                        TextView tv = new TextView(getApplicationContext());
                        tv.setLayoutParams(new TableRow.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        ));
                        // tv.setBackgroundResource(R.layout.cell_shape);
                        tv.setBackgroundResource(R.drawable.cell_shape);
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(18);
                        tv.setPadding(0, 5, 0, 5);

                        tv.setText(c.getString(j));

                        row.addView(tv);

                    }

                    c.moveToNext();

                    tableExtra.addView(row);

                }
                Toast.makeText(getApplicationContext(), "Data Fetched..", Toast.LENGTH_SHORT).show();
            }
        });


        


    }
}