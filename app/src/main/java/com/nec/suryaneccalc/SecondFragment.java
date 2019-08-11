package com.nec.suryaneccalc;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class SecondFragment extends Fragment {


    TextView subTextView[] = new TextView[6];
    static EditText[] lineTwo = new EditText[6];
    static EditText[] lineOne = new EditText[6];
    static int[] firstLine = new int[6];
    static int[] secondLine = new int[6];

    static int[] conversionSaver30 = new int[6];
    static int[] conversionSaver20 = new int[6];

    TextView descTextView;

    View rootView;
    static SharedPreferences sharedPreferences;

    static ArrayList<String> as1 = new ArrayList<>();
    static ArrayList<String> as2 = new ArrayList<>();
    static ArrayList<String> sub = new ArrayList<>();

    static String[] stringOne = new String[6];
    static String[] stringTwo = new String[6];

    static String a;
    static String b;


    Switch change20And30;

    static Boolean currentSwitchStatus = true;

    TextView textView20, textView30;

    public SecondFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_second, container, false);

        rootView = assigingToObjects(rootView);
        change20And30.setChecked(true);
        currentSwitchStatus = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            if (Objects.equals(MainActivity.pressedButton, "2")) {

                Log.i("ABC Entered in to if", " Init");
                a = "md2";
                b = "ob2";
            } else {

                a = "md1";
                b = "ob1";

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

                if (as1.get(i) != null) {
                    firstLine[i] = Integer.parseInt(as1.get(i));
                } else {
                    firstLine[i] = 11;
                }
                if (as2.get(i) != null) {
                    secondLine[i] = Integer.parseInt(as2.get(i));
                } else {
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

        Log.i("ABC Pressed ", " Save");
        takeToString();

        as1.clear();
        as2.clear();

        as1.addAll(Arrays.asList(stringOne));
        as2.addAll(Arrays.asList(stringTwo));

        Log.i("ABC as1", String.valueOf(as1));
        Log.i("ABC as2", String.valueOf(as2));

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
                int target = 30;
                if (!currentSwitchStatus) {
                    target = 20;
                }
                Log.i("ABC xyz1" , String.valueOf(xyz1));

                if (xyz1 <= target && xyz1 >= 0) {

                    if (!currentSwitchStatus) {
                        int value = Integer.parseInt(string1);
                        value = (int) Math.ceil((value / 2) * 3);
                        string1 = Integer.toString(value);
                    }
                    firstLine[i] = Integer.parseInt(string1);
                    stringOne[i] = string1;

                } else {
                    if (!(firstLine[i] == 11)) {
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

                if (xyz2 < 11 && xyz2 >= 0) {
                    stringTwo[i] = string2;
                    secondLine[i] = xyz2;
                } else {
                    if (!(secondLine[i] == 11)) {
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

        change20And30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView30.setTextColor(Color.parseColor("#C2185B"));
                    textView20.setTextColor(Color.parseColor("#808080"));
                    currentSwitchStatus = true;
                    Log.e("ABC true", "after");
                    changeMidMarks();
                    onTextChangeListenerLineTwo();
                } else {
                    textView20.setTextColor(Color.parseColor("#C2185B"));
                    textView30.setTextColor(Color.parseColor("#808080"));
                    currentSwitchStatus = false;
                    changeMidMarks();
                    Log.e("ABC false", "after");
                    onTextChangeListenerLineTwo();
                }
            }
        });
        onTextChangeListenerLineOne();
        onTextChangeListenerLineTwo();
    }


    @SuppressLint("SetTextI18n")
    public void changeMidMarks() {

        for (int i = 0; i < 6; i++) {
            int value;
            double val, va;
            int finalValue;
            if (!lineOne[i].getText().toString().isEmpty()) {


                value = Integer.parseInt(lineOne[i].getText().toString());

                if (currentSwitchStatus) {
                    if (conversionSaver20[i] == value) {
                        finalValue = conversionSaver30[i];
                    } else {
                        conversionSaver20[i] = value;
                        va = ((double) value / 2);
                        val = (va * 3);
                        finalValue = (int) Math.ceil(val);
                        conversionSaver30[i] = finalValue;
                    }
                } else {
                    if (conversionSaver30[i] == value) {
                        finalValue = conversionSaver20[i];
                    } else {
                        conversionSaver30[i] = value;
                        va = ((double) value / 3);
                        val = (va * 2);
                        finalValue = (int) Math.ceil(val);
                        conversionSaver20[i] = finalValue;
                    }
                }

                lineOne[i].setText(Integer.toString(finalValue));

            } else {
                conversionSaver30[i] = 0;
                conversionSaver20[i] = 0;
            }


        }


    }


    public void onTextChangeListenerLineOne() {
        for (int i = 0; i < 6; i++) {
            final int finalI = i;

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
                    Log.i("ABC Value",String.valueOf(value) +  "  " + lineOne[finalI].getText().toString() );
                    int target;
                    if (currentSwitchStatus) {
                        target = 30;
                    } else {
                        target = 20;
                    }


                    if (value > target) {
                        lineOne[finalI].setError(null);
                        lineOne[finalI].clearFocus();
                        lineOne[finalI].setError("Marks must be less than " + target);
                    } else {
                        save();
                    }
                }

            });
        }
    }

    public void onTextChangeListenerLineTwo() {

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
                        lineTwo[finalI].setError(null);//removes error
                        lineTwo[finalI].clearFocus();
                        lineTwo[finalI].setError("Marks must be less than 10");
                    } else {
                        save();
                    }
                }

            });
        }
    }


    public View assigingToObjects(View rootView) {


        subTextView[0] = rootView.findViewById(R.id.s1);
        subTextView[1] = rootView.findViewById(R.id.s2);
        subTextView[2] = rootView.findViewById(R.id.s3);
        subTextView[3] = rootView.findViewById(R.id.s4);
        subTextView[4] = rootView.findViewById(R.id.s5);
        subTextView[5] = rootView.findViewById(R.id.s6);
        descTextView = rootView.findViewById(R.id.ass1);
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
        change20And30 = rootView.findViewById(R.id.switch2);
        textView20 = rootView.findViewById(R.id.textView20Marks);
        textView30 = rootView.findViewById(R.id.textView30Marks);

        return rootView;
    }
}

