package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class add_income extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    RadioGroup typeChoice;
    Bundle bundle;
    EditText inputMoney;
    EditText note_ET;
    Intent it;
    String income_type="1"; // 1:salary,2:pocket,3:lucky,4:others
    String  money; // money
    String note ;
    static final int RESULT_B = 1;
    String total_type_str;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        typeChoice = (RadioGroup)findViewById(R.id.type_rg);
        typeChoice.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(typeChoice.getCheckedRadioButtonId()== R.id.radioButton_salary){
            income_type = getResources().getString(R.string.income_salary);
            total_type_str = "1";
        }
        else if(typeChoice.getCheckedRadioButtonId()==R.id.radioButton_pocket){
            income_type = getResources().getString(R.string.income_pocket_money);
            total_type_str = "2";
        }
        else if(typeChoice.getCheckedRadioButtonId()==R.id.radioButton_lucky){
            income_type = getResources().getString(R.string.income_lucky);
            total_type_str = "3";
        }
        else if(typeChoice.getCheckedRadioButtonId()==R.id.radioButton_others){
            income_type = getResources().getString(R.string.income_others);
            total_type_str = "4";
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
        setResult(RESULT_B, it);
        finish();
    }

    public void sure(View view){
        inputMoney = (EditText) findViewById(R.id.input_money);
        money = inputMoney.getText().toString();
        note_ET = (EditText) findViewById(R.id.expenditure_note_0);
        note = note_ET.getText().toString();
        it = new Intent();
        bundle = new Bundle();
        bundle.putString("bun_mark",getResources().getString(R.string.income));
        bundle.putString("bun_type",income_type);
        bundle.putString("bun_money",money);
        bundle.putString("bun_note",note);
        bundle.putString("bun_positive","1");
        bundle.putString("bun_totalT",total_type_str);
        it.putExtras(bundle);
        setResult(RESULT_B, it);
        finish();
    }
}