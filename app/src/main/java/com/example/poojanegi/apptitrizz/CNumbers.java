package com.example.poojanegi.apptitrizz;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by kkajnabi on 5/25/2016.
 */
public class CNumbers extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numbers);

        TextView foo = (TextView)findViewById(R.id.textViewkk);
        foo.setText(Html.fromHtml(getString(R.string.numbers)));
    }
}
