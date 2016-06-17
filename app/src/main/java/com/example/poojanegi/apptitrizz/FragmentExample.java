package com.example.poojanegi.apptitrizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by pooja negi on 3/2/2016.
 */
public class FragmentExample extends Fragment {

    ListView list;
    String[] text = { "Numbers","Hcf & Lcf", "Percentage", "Ratio","Permutation"};

    public int layoutpressed = -1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.concept, container, false);

        list = (ListView) rootView.findViewById(R.id.listView1);
        AdapterConcept adapter = new AdapterConcept(getActivity(), text);
        list.setAdapter(adapter);

        // LinearLayout definition and some layout properties...

     /*   list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {

                final LinearLayout ll = new LinearLayout(getActivity());
                ll.setOrientation(LinearLayout.VERTICAL);

    // it is supposed that the linear layout will be in a table.
    // if this is not the case for you change next line appropriately...
                ll.setLayoutParams(new TableRow.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                ll.setBackgroundResource(R.drawable.rounded_edges_normal);
                ll.setPadding(10, 10, 10, 10);
    // Now define the three button cases
                ll.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View arg0, MotionEvent arg1) {
                        if (arg1.getAction()==MotionEvent.ACTION_DOWN){
                            ll.setBackgroundResource(R.drawable.rounded_edges_pressed);
                            ll.setPadding(10, 10, 10, 10);
                            layoutpressed = arg0.getId();
                        }
                        else if (arg1.getAction()== MotionEvent.ACTION_UP){
                            ll.setBackgroundResource(R.drawable.rounded_edges_normal);
                            ll.setPadding(10, 10, 10, 10);
                            if(layoutpressed == arg0.getId()){

                                String[] text = { "CNumbers","CHcfLcm", "CPercentage", "CRatio","CPermutation"};
                                Intent in=new Intent("com.example.poojanegi.apptitrizz."+text[position]);
                                startActivity(in);

                                Toast.makeText(getActivity(), "clicked"+position, Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            ll.setBackgroundResource(R.drawable.rounded_edges_pressed);
                            ll.setPadding(10, 10, 10, 10);
                            layoutpressed = -1;
                        }
                        return true;
                    }
                });

            }
        });*/

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {
                String[] text = {"CNumbers", "CHcfLcm", "CPercentage", "CRatio", "CPermutation"};
                Intent in = new Intent("com.example.poojanegi.apptitrizz." + text[position]);
                startActivity(in);

            }
        });
        return rootView;
    }
    }

