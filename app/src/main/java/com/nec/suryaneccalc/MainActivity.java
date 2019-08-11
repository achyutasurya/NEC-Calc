package com.nec.suryaneccalc;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    static String pressedButton;
    static SharedPreferences sharedPreferences;
    static int firstTime = 1;
    static int whichOne;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    static int current;
    private Boolean exit = false;

    public static void updateSP(int updateValue) {

        sharedPreferences.edit().putInt("whichOne", updateValue).apply();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.nec.surneccalc", Context.MODE_PRIVATE);

        whichOne = sharedPreferences.getInt("whichOne", 0);

        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);


        displayView(whichOne);
        if (whichOne == 3) {
            updateSP(0);
        } else if (whichOne == 4) {
            updateSP(0);
        }  else if (whichOne == 5) {
            updateSP(0);
        }


        firstTime = sharedPreferences.getInt("firstTime", 0);
        Log.i("firstTime", String.valueOf(firstTime));
        if (firstTime == 0) {
            firstTime = 1;
            sharedPreferences.edit().putInt("firstTime", firstTime).apply();
            firstTimeNulling();
        }
        testingDisplay();
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }



    public void displayView(int position) {
        android.support.v4.app.Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new MainFragment();
                title = getString(R.string.title_home);
                current = 0;
                break;
            case 1:
                fragment = new ChangeSubjectsFragment();
                title = getString(R.string.title_subChange);
                current = 1;
                break;
            case 2:
                fragment = new BlankFragment();
                title = getString(R.string.title_messages);
                current = 2;
                break;
            case 3:
                fragment = new SingleSubjectAnaly();
                title = getString(R.string.title_single);
                current = 3;
                break;
            case 4:
                fragment = new AnalysisFragment();
                title = getString(R.string.title_analysis);
                current = 4;
                break;
            case 5:
                fragment = new AnalysisFragment();
                title = getString(R.string.title_analysis2);
                current = 5;
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public void testingDisplay() {


        ArrayList<String> as1 = new ArrayList<>();
        ArrayList<String> as2 = new ArrayList<>();
        ArrayList<String> as3 = new ArrayList<>();
        ArrayList<String> as4 = new ArrayList<>();
        ArrayList<String> ob1 = new ArrayList<>();
        ArrayList<String> ob2 = new ArrayList<>();
        ArrayList<String> md1 = new ArrayList<>();
        ArrayList<String> md2 = new ArrayList<>();

        try {
            as1 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("as1", ObjectSerializer.serialize(new ArrayList<String>())));
            as2 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("as2", ObjectSerializer.serialize(new ArrayList<String>())));
            as3 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("as3", ObjectSerializer.serialize(new ArrayList<String>())));
            as4 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("as4", ObjectSerializer.serialize(new ArrayList<String>())));
            ob1 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("ob1", ObjectSerializer.serialize(new ArrayList<String>())));
            ob2 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("ob2", ObjectSerializer.serialize(new ArrayList<String>())));
            md1 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("md1", ObjectSerializer.serialize(new ArrayList<String>())));
            md2 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("md2", ObjectSerializer.serialize(new ArrayList<String>())));


        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("as1", String.valueOf(as1));
        Log.i("as2", String.valueOf(as2));
        Log.i("as3", String.valueOf(as3));
        Log.i("as4", String.valueOf(as4));
        Log.i("ob1", String.valueOf(ob1));
        Log.i("ob2", String.valueOf(ob2));
        Log.i("md1", String.valueOf(md1));
        Log.i("md2", String.valueOf(md2));
    }

    public static void firstTimeNulling() {

        ArrayList<String> as1 = new ArrayList<>();
        ArrayList<String> as2 = new ArrayList<>();
        ArrayList<String> as3 = new ArrayList<>();
        ArrayList<String> as4 = new ArrayList<>();
        ArrayList<String> ob1 = new ArrayList<>();
        ArrayList<String> ob2 = new ArrayList<>();
        ArrayList<String> md1 = new ArrayList<>();
        ArrayList<String> md2 = new ArrayList<>();
        ArrayList<String> sub = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            as1.add(null);
            as2.add(null);
            as3.add(null);
            as1.add(null);
            as4.add(null);
            ob1.add(null);
            ob2.add(null);
            md1.add(null);
            md2.add(null);
            sub.add(null);

        }

        /*try {

            as1 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("as1", ObjectSerializer.serialize(new ArrayList<String>())));
            as2 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("as2", ObjectSerializer.serialize(new ArrayList<String>())));
            as3 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("as3", ObjectSerializer.serialize(new ArrayList<String>())));
            as4 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("as4", ObjectSerializer.serialize(new ArrayList<String>())));
            ob1 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("ob1", ObjectSerializer.serialize(new ArrayList<String>())));
            ob2 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("ob2", ObjectSerializer.serialize(new ArrayList<String>())));
            md1 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("md1", ObjectSerializer.serialize(new ArrayList<String>())));
            md2 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("md2", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            sharedPreferences.edit().putString("as1", ObjectSerializer.serialize(as1)).apply();
            sharedPreferences.edit().putString("as2", ObjectSerializer.serialize(as2)).apply();
            sharedPreferences.edit().putString("as3", ObjectSerializer.serialize(as3)).apply();
            sharedPreferences.edit().putString("as4", ObjectSerializer.serialize(as4)).apply();
            sharedPreferences.edit().putString("ob1", ObjectSerializer.serialize(ob1)).apply();
            sharedPreferences.edit().putString("ob2", ObjectSerializer.serialize(ob2)).apply();
            sharedPreferences.edit().putString("md1", ObjectSerializer.serialize(md1)).apply();
            sharedPreferences.edit().putString("md2", ObjectSerializer.serialize(md2)).apply();
            sharedPreferences.edit().putString("subjectNames", ObjectSerializer.serialize(sub)).apply();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {

        if (current == 0) {
            if (exit) {
                finish();
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
            } else {
                Toast.makeText(this, "Press Back again to Exit.",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        exit=false;
                    }
                }, 2000);
            }
        }
        else {
            finish();
            Intent a = new Intent(MainActivity.this, MainActivity.class);
            startActivity(a);
        }
    }
}
