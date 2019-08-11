package com.nec.suryaneccalc;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class FirstFragment extends Fragment {

    TextView ass1, ass2;
    TextView subTextView[] = new TextView[6];
    static EditText[] lineTwo = new EditText[6];
    static EditText[] lineOne = new EditText[6];

    static int[] firstLine = new int[6];
    static int[] secondLine = new int[6];

    View rootView;
    static SharedPreferences sharedPreferences;

    static ArrayList<String> as1 = new ArrayList<>();
    static ArrayList<String> as2 = new ArrayList<>();
    static ArrayList<String> sub = new ArrayList<>();

    static String[] stringOne = new String[6];
    static String[] stringTwo = new String[6];

    static String a;
    static String b;


    public FirstFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_first, container, false);

        rootView = assigingToObjects(rootView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            if (Objects.equals(MainActivity.pressedButton, "2")) {

                ass1.setText("A-3");
                ass2.setText("A-4");
                a = "as3";
                b = "as4";
            } else {

                a = "as1";
                b = "as2";

            }
        }

        sharedPreferences = getActivity().getSharedPreferences("com.nec.surneccalc", Context.MODE_PRIVATE);
        try {

            as1 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(a, ObjectSerializer.serialize(new ArrayList<String>())));
            as2 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(b, ObjectSerializer.serialize(new ArrayList<String>())));
            sub = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("subjectNames", ObjectSerializer.serialize(new ArrayList<String>())));



            for (int i = 0; i < 6; i++) {
                lineOne[i].setText(as1.get(i));
                lineTwo[i].setText(as2.get(i));
                subTextView[i].setText(sub.get(i));


                if(as1.get(i)!= null ) {
                    firstLine[i] = Integer.parseInt(as1.get(i));
                }
                else {
                    firstLine[i] = 11;
                }
                if(as2.get(i)!= null) {
                    secondLine[i] = Integer.parseInt(as2.get(i));
                }
                else {
                    secondLine[i] = 11;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        onTextChangeListener();


        return rootView;
    }

    public static void save() {

        takeToString();

        as1.clear();
        as2.clear();


        as1.addAll(Arrays.asList(stringOne));
        as2.addAll(Arrays.asList(stringTwo));

        try {
            sharedPreferences.edit().putString(a, ObjectSerializer.serialize(as1)).apply();
            sharedPreferences.edit().putString(b, ObjectSerializer.serialize(as2)).apply();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void takeToString() {

        String string1, string2;
        int i;

        for (i = 0; i < 6; i++) {

            string1 = lineOne[i].getText().toString();
            if (!string1.isEmpty()) {

                int xyz1 = Integer.parseInt(string1);

                if(xyz1 < 11 && xyz1 >= 0){
                    stringOne[i] = string1;
                    firstLine[i] = xyz1;
                }
                else{
                    if(!(firstLine[i] == 11)) {
                        stringOne[i] = Integer.toString(firstLine[i]);
                    } else {
                        stringOne[i] = null;
                    }
                }

            } else {
                stringOne[i] = null;
            }


            string2 = lineTwo[i].getText().toString();

            if (!string2.isEmpty()) {
                int xyz2 = Integer.parseInt(string2);

                if(xyz2 < 11 && xyz2 >= 0){
                    stringTwo[i] = string2;
                    secondLine[i] = xyz2;
                }
                else{
                    if(!(secondLine[i] == 11)) {
                        stringTwo[i] = Integer.toString(secondLine[i]);

                    } else {
                        stringTwo[i] = null;
                    }
                }

            } else {
                stringTwo[i] = null;
            }

        }


    }

    public void onTextChangeListener() {

        for (int i = 0; i < 6; i++) {

            final int finalI = i;
            lineTwo[i].addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    int value = 0;
                    if (!lineTwo[finalI].getText().toString().isEmpty()) {

                        value = Integer.parseInt(lineTwo[finalI].getText().toString());
                    }

                    if (value > 10) {
                        lineTwo[finalI].setError("Marks must be less than 10");
                    } else {
                        save();

                    }
                }

            });

            lineOne[i].addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    int value = 0;
                    if (!lineOne[finalI].getText().toString().isEmpty()) {

                        value = Integer.parseInt(lineOne[finalI].getText().toString());
                    }

                    if (value > 10) {
                        lineOne[finalI].setError("Marks must be less than 10");
                    } else {
                        save();
                    }
                }

            });
        }
    }


    public View assigingToObjects(View rootView) {

        ass1 = rootView.findViewById(R.id.ass1);
        ass2 = rootView.findViewById(R.id.ass2);
        subTextView[0] = rootView.findViewById(R.id.s1);
        subTextView[1] = rootView.findViewById(R.id.s2);
        subTextView[2] = rootView.findViewById(R.id.s3);
        subTextView[3] = rootView.findViewById(R.id.s4);
        subTextView[4] = rootView.findViewById(R.id.s5);
        subTextView[5] = rootView.findViewById(R.id.s6);
        lineTwo[0] = rootView.findViewById(R.id.x1);
        lineTwo[1] = rootView.findViewById(R.id.x2);
        lineTwo[2] = rootView.findViewById(R.id.x3);
        lineTwo[3] = rootView.findViewById(R.id.x4);
        lineTwo[4] = rootView.findViewById(R.id.x5);
        lineTwo[5] = rootView.findViewById(R.id.x6);
        lineOne[0] = rootView.findViewById(R.id.y1);
        lineOne[1] = rootView.findViewById(R.id.y2);
        lineOne[2] = rootView.findViewById(R.id.y3);
        lineOne[3] = rootView.findViewById(R.id.y4);
        lineOne[4] = rootView.findViewById(R.id.y5);
        lineOne[5] = rootView.findViewById(R.id.y6);

        return rootView;
    }

}
