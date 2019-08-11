package com.nec.suryaneccalc;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    View rootView;
    CardView entry1, entry2, analysis1, analysis2, singleAnalysis;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);



        entry1=rootView.findViewById(R.id.mid1Card);
        entry2=rootView.findViewById(R.id.mid2Card);
        analysis1=rootView.findViewById(R.id.analy1Card);
        analysis2=rootView.findViewById(R.id.analy2card);
        singleAnalysis=rootView.findViewById(R.id.singleCard);

        entry1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                MainActivity.pressedButton = "1";
                Intent intent = new Intent(getActivity(), MarksEntryActivity.class);
                startActivity(intent);
            }
        });

        entry2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                MainActivity.pressedButton = "2";
                Intent intent = new Intent(getActivity(), MarksEntryActivity.class);
                startActivity(intent);

            }
        });

        analysis1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                MainActivity.updateSP(4);
                MainActivity.pressedButton = "3";
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        analysis2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                MainActivity.updateSP(5);
                MainActivity.pressedButton = "4";
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });

        singleAnalysis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                MainActivity.updateSP(3);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);



            }
        });

        return rootView;
    }

}
