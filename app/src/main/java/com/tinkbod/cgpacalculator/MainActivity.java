package com.tinkbod.cgpacalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Float> calcgpa = new ArrayList<>();
    ArrayList<String> alister = new ArrayList<>();
    ArrayAdapter<String> apter;

    ListView lvv;
    TextView tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       lvv = (ListView) findViewById(R.id.list2);
        apter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout
                .simple_spinner_item, alister);
        lvv.setAdapter(apter);


        Button bton = (Button) findViewById(R.id.button);
        Button bton1 = (Button) findViewById(R.id.button5);
        tb = (TextView) findViewById(R.id.textView2);
        bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yintent = new Intent(MainActivity.this, AddSemester.class);
                startActivityForResult(yintent, 1);


            }
        });

        bton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(calcgpa.isEmpty()){

                  Toast.makeText(MainActivity.this, "Please Add a Semester", Toast.LENGTH_SHORT).show
                          ();
                } else if(!calcgpa.isEmpty()){
                            float fresult = 0;
                        for (float er : calcgpa) {
                            fresult += er;

                        }

                        float dfresult = fresult/calcgpa.size();
                        Toast.makeText(MainActivity.this, "The CGPA is "+dfresult, Toast
                                .LENGTH_SHORT).show();

                    tb.setText("The CGPA is : "+Float.toString(dfresult));

                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){


            if(resultCode == Activity.RESULT_OK){
                tb.setText("");


               String re = data.getStringExtra("result");

                 alister.add("Semester GPA : "+re+"\n");

               apter.notifyDataSetChanged();


                Toast.makeText(this, "Semester GPA is "+re, Toast.LENGTH_SHORT).show();


               float gpResult = Float.parseFloat(re);
               calcgpa.add(gpResult);





               /**
               final LinearLayout ly= new LinearLayout(this);
                TextView dynaTextV = new TextView(this);
                dynaTextV.setText("semester 1 : "+gpResult+"\n");
                dynaTextV.setTextSize(22);
                dynaTextV.setPadding(20, 300, 20, 100);


                ly.addView(dynaTextV);
                this.setContentView(ly, new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT

                ));
**/
            } else if(resultCode == Activity.RESULT_CANCELED) {

                Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
            }
        }






    }

}
