package com.tinkbod.cgpacalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

public class AddSemester extends AppCompatActivity {

    //SparseIntArray st = new SparseIntArray();
    ArrayList<Integer> calGp = new ArrayList<>();
    ArrayList<Integer> unitGp = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);



        Button btn2 = (Button) findViewById(R.id.button2);
        LayoutInflater inflater = (LayoutInflater) AddSemester.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View popview = inflater.inflate(R.layout.semester, null);

        float density = AddSemester.this.getResources().getDisplayMetrics().density;
        final PopupWindow pw = new PopupWindow(popview, (int) density * 300, (int)
                density * 400, true);

        Button btn4 = (Button) popview.findViewById(R.id.button3);

        Button btn5 = (Button) findViewById(R.id.button4);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {







                pw.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


                pw.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {


                            pw.dismiss();
                            return true;
                        }
                        return false;
                    }
                });

                pw.setOutsideTouchable(true);
                pw.showAtLocation(popview, Gravity.CENTER, 0, 0);

            }



        });

        ListView lv = (ListView) findViewById(R.id.list);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout
                .simple_spinner_item, arrayList);
            lv.setAdapter(adapter);
        btn4.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {




                EditText tv = (EditText) popview.findViewById(R.id.editText);
               EditText tv1 = (EditText) popview.findViewById(R.id.editText2);

                String nstring1 = tv1.getText().toString().toUpperCase();
                String asr = tv.getText().toString();
                int nstring = 0;


                if(!TextUtils.isEmpty(asr) && TextUtils.isDigitsOnly(asr))
                {
                    nstring = Integer.parseInt(asr);
                    unitGp.add(nstring);


                }




                int grd = setGrade(nstring1);


                if ( nstring1.isEmpty() || asr.isEmpty()) {
                    Toast.makeText(AddSemester.this, "Invalid Input", Toast
                            .LENGTH_SHORT).show();


                    pw.dismiss();

                }  else if ((grd == 10 || nstring > 6)) {
                    Toast.makeText(AddSemester.this, "Invalid Input", Toast
                            .LENGTH_SHORT).show();
                    pw.dismiss();
                } else {
                    calGp.add(nstring * grd);

                    //st.put(nstring, grd);
                    Toast.makeText(AddSemester.this, "Course Added", Toast
                            .LENGTH_SHORT).show();
                    pw.dismiss();

                   /**
                    LinearLayout lly= new LinearLayout(AddSemester.this);
                    TextView dynaTextVs = new TextView(AddSemester.this);
                    dynaTextVs.setText("Grade : "+grd+", Unit : "+nstring+"\n");
                    dynaTextVs.setTextSize(22);
                    dynaTextVs.setPadding(20, 300, 20, 100);
**/

                    //lly.addView(dynaTextVs);
                    arrayList.add("Added Grade : "+nstring1+", Unit : "+nstring+"\n");
                    //sv.addView(lly);
                    adapter.notifyDataSetChanged();
                    tv.setText("");
                    tv1.setText("");
                    //nstring = 0;
                    //asr = "";
                    //nstring1 = "";



                }


            }

        });



        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/**
 for (int i=0; i < st.size(); i++){

 int key = st.keyAt(i);
 int mim = st.get(key);
 int nim = key *  mim;



 }
 **/
                // for java 8 and newest api int summ = Arrays.stream(calGp).sum();
                    if(!calGp.isEmpty() && !unitGp.isEmpty()){
                float summ = 0;
                float usum = 0;

                for (int ip : calGp) {

                    summ += ip;
                }

                for (int ut : unitGp) {

                    usum += ut;
                }
                float gp = summ / usum;

                String res = Float.toString(gp);

                Intent rintent = new Intent();
                rintent.putExtra("result", res);
                setResult(RESULT_OK, rintent);
                finish();

            } else if(calGp.isEmpty() || unitGp.isEmpty()){


            Toast.makeText(AddSemester.this, "Please Add a Course", Toast.LENGTH_SHORT).show();
            }

            }
        });



    }

    public int setGrade(String a ) {
        int b;
        switch(a) {
            case "A" :
                b = 5;
                break;
            case "B":
                b = 4;
                break;
            case "C":
                b = 3;
                break;
            case "D":
                b = 2;
                break;
            case "E":
                b = 1;
                break;
            case "F":
                b = 0;
                break;
            default:
                b = 10;

        }

return b;

    }


}
