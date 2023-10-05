package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class look_chart extends AppCompatActivity {

    Bundle bundle;
    int salary = 0;
    int pocket = 0;
    int lucky = 0;
    int others_income = 0;
    int food = 0;
    int drinks = 0;
    int transport = 0;
    int medical = 0;
    int enter = 0;
    int others_expend = 0;
    Button btn_chart;

    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView t8;
    TextView t9;
    TextView t10;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_chart);

        btn_chart = (Button)findViewById(R.id.button_chart);
        btn_chart.setEnabled(false);

        bundle = this.getIntent().getExtras();

        salary = bundle.getInt("bun_1");
        pocket = bundle.getInt("bun_2");
        lucky = bundle.getInt("bun_3");
        others_income = bundle.getInt("bun_4");
        food = bundle.getInt("bun_5");
        drinks = bundle.getInt("bun_6");
        transport = bundle.getInt("bun_7");
        medical = bundle.getInt("bun_8");
        enter = bundle.getInt("bun_9");
        others_expend = bundle.getInt("bun_10");

        t1 = (TextView) findViewById(R.id.te_1);
        t2 = (TextView) findViewById(R.id.te_2);
        t3 = (TextView) findViewById(R.id.te_3);
        t4 = (TextView) findViewById(R.id.te_4);
        t5 = (TextView) findViewById(R.id.te_5);
        t6 = (TextView) findViewById(R.id.te_6);
        t7 = (TextView) findViewById(R.id.te_7);
        t8 = (TextView) findViewById(R.id.te_8);
        t9 = (TextView) findViewById(R.id.te_9);
        t10 = (TextView) findViewById(R.id.te_10);

        show();
    }

    public void show(){
        t1.setText(""+salary);
        t2.setText(""+pocket);
        t3.setText(""+lucky);
        t4.setText(""+others_income);
        t5.setText(""+food);
        t6.setText(""+drinks);
        t7.setText(""+transport);
        t8.setText(""+medical);
        t9.setText(""+enter);
        t10.setText(""+others_expend);
    }

    public void keep(View view){
        finish();
    }
}