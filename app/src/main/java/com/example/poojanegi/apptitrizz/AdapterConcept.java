package com.example.poojanegi.apptitrizz;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by pooja negi on 4/16/2016.
 */
public class AdapterConcept extends ArrayAdapter<String> {

    private final Activity _context;
    private final String[] _text;
    View rowView;
    int LayoutResourceid;


    public AdapterConcept(Activity context, String[] text) {
        super(context, R.layout.list_conspt, text);
        this._context = context;
        this._text = text;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = _context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.list_conspt, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.tv1);

        txtTitle.setText(_text[position]);

        String s = _text[position];



        return rowView;


    }


}
