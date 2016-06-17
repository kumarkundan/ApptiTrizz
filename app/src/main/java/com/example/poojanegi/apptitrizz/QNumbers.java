package com.example.poojanegi.apptitrizz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kkajnabi on 5/26/2016.
 */
public class QNumbers extends Activity {

    HandlerNumbers mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.que);

        final TextView q = (TextView) findViewById(R.id.quest);
        final Button a1 = (Button) findViewById(R.id.ans1);
        final Button a2 = (Button) findViewById(R.id.ans2);
        final Button a3 = (Button) findViewById(R.id.ans3);
        final Button a4 = (Button) findViewById(R.id.ans4);
        final Button ca = (Button) findViewById(R.id.cans);
        Button next = (Button) findViewById(R.id.next);
        Button prev = (Button) findViewById(R.id.prev);

        mDatabaseHelper = new HandlerNumbers(this);
        mDatabaseHelper.createDatabase();
        mDatabaseHelper.openDatabase();

        final ArrayList<DataNumbers> s = mDatabaseHelper.getAllStudents();
        final int[] i = {1};
        final int[] j = {0};
        final int[] k = {0};
        final int n = s.size();

        q.setText(s.get(0).getQuestion());
        a1.setText(s.get(0).getAnswer1());
        a2.setText(s.get(0).getAnswer2());
        a3.setText(s.get(0).getAnswer3());
        a4.setText(s.get(0).getAnswer4());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                a1.setBackgroundColor(Color.BLUE);
                a2.setBackgroundColor(Color.BLUE);
                a3.setBackgroundColor(Color.BLUE);
                a4.setBackgroundColor(Color.BLUE);

                if (i[0] < n) {
                    q.setText(s.get(i[0]).getQuestion());
                    a1.setText(s.get(i[0]).getAnswer1());
                    a2.setText(s.get(i[0]).getAnswer2());
                    a3.setText(s.get(i[0]).getAnswer3());
                    a4.setText(s.get(i[0]).getAnswer4());
                    j[0] = i[0];
                    k[0] = i[0];
                    i[0]++;
                } else {

                    Intent in = new Intent(getApplicationContext(), Home.class);
                    startActivity(in);
                    finish();
                }
            }
        });

        final int flag[] = {0};
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                a1.setBackgroundColor(Color.BLUE);
                a2.setBackgroundColor(Color.BLUE);
                a3.setBackgroundColor(Color.BLUE);
                a4.setBackgroundColor(Color.BLUE);

                if (flag[0] == 0 && k[0] > 0) {
                    k[0]--;
                    flag[0] = 1;
                }
                if (k[0] < 0) {
                    Intent in = new Intent(getApplicationContext(), Home.class);
                    startActivity(in);
                    finish();
                } else {

                    q.setText(s.get(k[0]).getQuestion());
                    a1.setText(s.get(k[0]).getAnswer1());
                    a2.setText(s.get(k[0]).getAnswer2());
                    a3.setText(s.get(k[0]).getAnswer3());
                    a4.setText(s.get(k[0]).getAnswer4());
                    //  i[0]=k[0];
                    Log.v("i kaaaaaaaaaaa v", "" + i[0]);
                    //  j[0]=i[0];
                    i[0] = k[0];
                    j[0] = k[0];
                    k[0]--;
                }

            }
        });

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = a1.getText().toString();

                if (s1.equals(s.get(j[0]).getCanswer())) {

                    a1.setBackgroundColor(Color.GREEN);
                } else {
                    a1.setBackgroundColor(Color.RED);
                }
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = a2.getText().toString();

                if (s1.equals(s.get(j[0]).getCanswer())) {

                    a2.setBackgroundColor(Color.GREEN);
                } else {
                    a2.setBackgroundColor(Color.RED);
                }
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = a3.getText().toString();

                if (s1.equals(s.get(j[0]).getCanswer())) {

                    a3.setBackgroundColor(Color.GREEN);
                } else {
                    a3.setBackgroundColor(Color.RED);
                }
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = a4.getText().toString();

                if (s1.equals(s.get(j[0]).getCanswer())) {

                    a4.setBackgroundColor(Color.GREEN);
                } else {
                    a4.setBackgroundColor(Color.RED);
                }
            }
        });

        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(QNumbers.this)
                        .setTitle("Answer/Explanation")
                        .setMessage(s.get(j[0]).getSolution())
                        .setNeutralButton("Go Back", null)
                        .show();
                Log.v("dialog text", s.get(j[0]).getSolution());
            }
        });

    }
}
