package com.nec.suryaneccalc;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeSubjectsFragment extends Fragment {


    View rootView;
    static EditText[] editSubjectNames = new EditText[6];
    static String[] subjectNames = new String[6];
    static String[] subjectNamesForSP = new String[6];
    static SharedPreferences sharedPreferences;
    static ArrayList<String> sub = new ArrayList<>();


    public ChangeSubjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_change_subjects, container, false);

        assigningValues(rootView);
        gettingValuesFromSharedPreferences();
        onTextChangeListenerLineTwo();


        return rootView;
    }

    public void onTextChangeListenerLineTwo() {

        for (int i = 0; i < 6; i++) {
            editSubjectNames[i].addTextChangedListener(new TextWatcher() {

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
                    saveSubjectNames();
                }

            });
        }
    }

    public View assigningValues(View rootView) {

        editSubjectNames[0] = rootView.findViewById(R.id.ed1);
        editSubjectNames[1] = rootView.findViewById(R.id.ed2);
        editSubjectNames[2] = rootView.findViewById(R.id.ed3);
        editSubjectNames[3] = rootView.findViewById(R.id.ed4);
        editSubjectNames[4] = rootView.findViewById(R.id.ed5);
        editSubjectNames[5] = rootView.findViewById(R.id.ed6);

        return rootView;
    }

    public void gettingValuesFromSharedPreferences() {

        sharedPreferences = getActivity().getSharedPreferences("com.nec.surneccalc", Context.MODE_PRIVATE);
        try {

            sub = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("subjectNames", ObjectSerializer.serialize(new ArrayList<String>())));
            for (int i = 0; i < 6; i++) {
                editSubjectNames[i].setText(sub.get(i));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void savingValuesToSharedPreferences() {
        sub.clear();
        sub.addAll(Arrays.asList(subjectNamesForSP));
        try {
            sharedPreferences.edit().putString("subjectNames", ObjectSerializer.serialize(sub)).apply();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSubjectNames() {
        takingValuesTOString();
        savingValuesToSharedPreferences();
    }

    public static void takingValuesTOString(){
        String temp;
        for (int i = 0; i < 6; i++) {
            temp = editSubjectNames[i].getText().toString();
            if (!temp.isEmpty()) {
                subjectNames[i] = temp;
                subjectNamesForSP[i] = temp;
            } else {
                subjectNames[i] = "SUB-" + i;
                subjectNamesForSP[i] = null;
            }
        }
    }
}
