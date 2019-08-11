package com.nec.suryaneccalc;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleSubjectAnaly extends Fragment {


    View rootView;
    EditText editTextAs1, editTextAs2, editTextMid, editTextObj;
    TextView textViewTotal, textViewFor75, textViewFor25;
    static int as1, as2, mid, obj, total, mid20, as, for25, for75;

    public SingleSubjectAnaly() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_single_subject_analy, container, false);



        editTextAs1 = rootView.findViewById(R.id.editTextAS1);
        editTextAs2 = rootView.findViewById(R.id.editTextAS2);
        editTextMid = rootView.findViewById(R.id.editTextMID);
        editTextObj = rootView.findViewById(R.id.editTextObj);
        textViewTotal = rootView.findViewById(R.id.textViewTotal);
        textViewFor25 = rootView.findViewById(R.id.textViewShow25);
        textViewFor75 = rootView.findViewById(R.id.textViewShow75);

        setBeforeMarks();
        Log.i("as1 : " + Integer.toString(as1) + "  as2 : " + Integer.toString(as2) + "  mid", Integer.toString(mid) + "  obj : " + Integer.toString(obj));

        editTextAs1.addTextChangedListener(new TextWatcher() {

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
                onTextCalling();
            }
        });

        editTextAs2.addTextChangedListener(new TextWatcher() {

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
                onTextCalling();
            }
        });


        editTextMid.addTextChangedListener(new TextWatcher() {

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
                onTextCalling();
            }
        });


        editTextObj.addTextChangedListener(new TextWatcher() {

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
                onTextCalling();
            }
        });

        return rootView;
    }


    @SuppressLint("SetTextI18n")
    public void setBeforeMarks() {

        int count = 0;

        if (as1 != 0) {
            editTextAs1.setText(Integer.toString(as1));
            count = 1;
        }
        if (as2 != 0) {
            editTextAs2.setText(Integer.toString(as2));
            count = 1;
        }
        if (mid != 0) {
            editTextMid.setText(Integer.toString(mid));
            count = 1;
        }
        if (obj != 0) {
            editTextObj.setText(Integer.toString(obj));
            count = 1;
        }
        if (count == 1) {
            calculation();
        }


    }

    public void onTextCalling() {
        calculation();
    }

    public void getMarks() {

        if (!editTextAs1.getText().toString().isEmpty()) {

            if (Integer.parseInt(editTextAs1.getText().toString()) >= 0 && Integer.parseInt(editTextAs1.getText().toString()) <= 10) {
                as1 = Integer.parseInt(editTextAs1.getText().toString());
            } else {
                editTextAs1.setError("Marks must be less than 10");
                as1 = 0;
            }
        } else {
            as1 = 0;
        }

        if (!editTextAs2.getText().toString().isEmpty()) {

            if (Integer.parseInt(editTextAs2.getText().toString()) >= 0 && Integer.parseInt(editTextAs2.getText().toString()) <= 10) {
                as2 = Integer.parseInt(editTextAs2.getText().toString());
            } else {
                editTextAs2.setError("Marks must be less than 10");
                as2 = 0;
            }

        } else {
            as2 = 0;
        }

        if (!editTextMid.getText().toString().isEmpty()) {

            if (Integer.parseInt(editTextMid.getText().toString()) >= 0 && Integer.parseInt(editTextMid.getText().toString()) <= 30) {
                mid = Integer.parseInt(editTextMid.getText().toString());
            } else {
                editTextMid.setError("Marks must be less than 30");
                mid = 0;
            }


        } else {
            mid = 0;
        }

        if (!editTextObj.getText().toString().isEmpty()) {

            if (Integer.parseInt(editTextObj.getText().toString()) >= 0 && Integer.parseInt(editTextObj.getText().toString()) <= 10) {
                obj = Integer.parseInt(editTextObj.getText().toString());
            } else {
                editTextObj.setError("Marks must be less than 10");
                obj = 0;
            }
        } else {
            obj = 0;
        }

        System.out.print("as1 : " + as1 + "  as2 : " + as2 + "  mid : " + mid + "  obj : " + obj);

        //Log.i("as1 : " + Integer.toString(as1) + "  as2 : " + Integer.toString(as2) + "  mid", Integer.toString(mid) + "  obj : " + Integer.toString(obj));

    }

    @SuppressLint("SetTextI18n")
    public void calculation() {
        getMarks();
        double a;
        a = ((double) mid / 3) * 2;
        mid20 = (int) Math.ceil(a);
        as = as1 > as2 ? as1 : as2;
        total = as + mid20 + obj;
        for25 = (int) Math.ceil(total * 0.25);
        for75 = (int) Math.ceil(total * 0.75);
        textViewTotal.setText(Integer.toString(total));
        textViewFor25.setText(Integer.toString(for25));
        textViewFor75.setText(Integer.toString(for75));
    }
}
