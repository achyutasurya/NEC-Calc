package com.nec.suryaneccalc;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalysisFragment extends Fragment {

    View rootView;
    static ArrayList<String> as1 = new ArrayList<>();
    static ArrayList<String> as2 = new ArrayList<>();
    static ArrayList<String> obj = new ArrayList<>();
    static ArrayList<String> mid = new ArrayList<>();
    static ArrayList<String> totalCount = new ArrayList<>();
    static ArrayList<String> per75Count = new ArrayList<>();
    static ArrayList<String> per25Count = new ArrayList<>();
    static ArrayList<String> sub = new ArrayList<>();
    static ArrayList<String> targetMarksArray = new ArrayList<>();

    int[] assFinalMarks = new int[6];
    int[] midMarks = new int[6];
    int[] objMarks = new int[6];
    int[] totalMarks = new int[6];
    String[] subjectNames= new String[6];
    static int[] noTarget = new int[6];
    static Boolean[] isThereMarks = new Boolean[6];

    int[] twentyFive = new int[6];
    int[] seventyFive = new int[6];

    static String a, b, c, d;

    RecyclerView recyclerView;

    int temp;
    SharedPreferences sharedPreferences;



    public AnalysisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_analysis, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            if (Objects.equals(MainActivity.pressedButton, "3")) {
                a = "as1";
                b = "as2";
                c = "ob1";
                d = "md1";
            } else {

                a = "as3";
                b = "as4";
                c = "ob2";
                d = "md2";

            }
        }

        sharedPreferences = getActivity().getSharedPreferences("com.nec.surneccalc", Context.MODE_PRIVATE);

        try {
            as1.clear();
            as2.clear();
            mid.clear();
            obj.clear();
            totalCount.clear();
            per25Count.clear();
            per75Count.clear();
            sub.clear();
            targetMarksArray.clear();

            as1 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(a, ObjectSerializer.serialize(new ArrayList<String>())));
            as2 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(b, ObjectSerializer.serialize(new ArrayList<String>())));
            obj = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(c, ObjectSerializer.serialize(new ArrayList<String>())));
            mid = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(d, ObjectSerializer.serialize(new ArrayList<String>())));
            sub = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("subjectNames", ObjectSerializer.serialize(new ArrayList<String>())));

            for(int i=0; i<6; i++){


                if(as1.get(i) == null){
                    as1.set(i,"0");
                }
                if(as2.get(i) == null){
                    as2.set(i,"0");
                }
                if(obj.get(i) == null){
                    obj.set(i,"0");
                }
                if(mid.get(i) == null){
                    mid.set(i,"0");
                }
                if(sub.get(i) == null){
                    subjectNames[i] = "Sub-"+(i+1);
                }
                else {
                    subjectNames[i] = sub.get(i);
                }


                if(Integer.valueOf(as1.get(i)) >= Integer.valueOf(as2.get(i))) {
                    assFinalMarks[i] = Integer.valueOf(as1.get(i));
                }
                else{
                    assFinalMarks[i] = Integer.valueOf(as2.get(i));
                }

                temp = Integer.valueOf(mid.get(i));
                double value = ((double) temp/3)*2;
                midMarks[i] = (int) Math.ceil(value);
                objMarks[i] = Integer.valueOf(obj.get(i));
                totalMarks[i] = assFinalMarks[i] + midMarks[i] + objMarks[i];
                value = totalMarks[i] * 0.25;
                twentyFive[i] = (int) Math.ceil(value);
                value = totalMarks[i] * 0.75;
                seventyFive[i] = (int) Math.ceil(value);

                if(seventyFive[i]<19){
                    targetMarksArray.add(Integer.toString(19-seventyFive[i]));
                    if(seventyFive[i] < 11){
                        noTarget[i] = 2;
                    }else {
                        noTarget[i] = 1;
                    }
                }
                else {
                    targetMarksArray.add("Targets");
                    noTarget[i] = 0;
                }

                if(totalMarks[i] == 0){
                    isThereMarks[i] = false;
                }else {
                    isThereMarks[i] = true;
                }

            }
            for (int i : totalMarks)
            {
                totalCount.add(String.valueOf(i));
            }
            for (int i : twentyFive)
            {
                per25Count.add(String.valueOf(i));
            }
            for (int i : seventyFive)
            {
                per75Count.add(String.valueOf(i));
            }
            initView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    public void initView(){

        recyclerView= rootView.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> countries=new ArrayList<>();
        for(int i=0; i<6; i++){

            countries.add(subjectNames[i]);

        }

        RecyclerView.Adapter adapter= new DataAdapter(countries);
        recyclerView.setAdapter(adapter);

    }
}
