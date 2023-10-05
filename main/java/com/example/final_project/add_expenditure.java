package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class add_expenditure extends AppCompatActivity {

    static final int RESULT_C = 1;
    RadioGroup typeChoice;
    Bundle bundle;
    EditText inputMoney;
    EditText note_ET;
    Intent it;
    String expenditure_type="1"; // 1:food,2:drinks,3:transport,4:medical,5:enter,6:others
    String  money; // money
    String note ;
    String total_type_str;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenditure);

        typeChoice = (RadioGroup)findViewById(R.id.type_rg);
        typeChoice.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(typeChoice.getCheckedRadioButtonId()== R.id.radioButton_food){
            expenditure_type = getResources().getString(R.string.expenditure_food);
            total_type_str = "5";
        }
        else if(typeChoice.getCheckedRadioButtonId()==R.id.radioButton_drinks){
            expenditure_type = getResources().getString(R.string.expenditure_drinks);
            total_type_str = "6";
        }
        else if(typeChoice.getCheckedRadioButtonId()==R.id.radioButton_transport){
            expenditure_type = getResources().getString(R.string.expenditure_transport);
            total_type_str = "7";
        }
        else if(typeChoice.getCheckedRadioButtonId()==R.id.radioButton_medical){
            expenditure_type = getResources().getString(R.string.expenditure_medical);
            total_type_str = "8";
        }
        else if(typeChoice.getCheckedRadioButtonId()==R.id.radioButton_entertainment){
            expenditure_type = getResources().getString(R.string.expenditure_entertainment);
            total_type_str = "9";
        }
        else if(typeChoice.getCheckedRadioButtonId()==R.id.radioButton_others){
            expenditure_type = getResources().getString(R.string.expenditure_others);
            total_type_str = "10";
        }
    }

    public void back(View view){
        it = new Intent();
        bundle = new Bundle();
        bundle.putString("bun_mark","");
        bundle.putString("bun_type","");
        bundle.putString("bun_money","");
        bundle.putString("bun_note","");
        bundle.putString("bun_positive","");
        bundle.putString("bun_totalT","");
        it.putExtras(bundle);
        setResult(RESULT_C, it);
        finish();
    }

    public void sure(View view){
        inputMoney = (EditText) findViewById(R.id.input_money);
        money = inputMoney.getText().toString();
        note_ET = (EditText) findViewById(R.id.expenditure_note_0);
        it = new Intent();
        bundle = new Bundle();
        bundle.putString("bun_mark",getResources().getString(R.string.expenditure));
        bundle.putString("bun_type",expenditure_type);
        bundle.putString("bun_money",money);
        bundle.putString("bun_note",note);
        bundle.putString("bun_positive","0");
        bundle.putString("bun_totalT",total_type_str);
        it.putExtras(bundle);
        setResult(RESULT_C, it);
        finish();
    }


}