package com.example.final_project;

import static com.example.final_project.add_expenditure.RESULT_C;
import static com.example.final_project.add_income.RESULT_B;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.content.Intent;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnTouchListener,View.OnClickListener{

    View page;

    String input_mark = "";
    String input_type = "";
    String input_money = "";
    String input_note = "NO";
    String input_positive;
    String input_totalT;
    int income_sum = 0;
    int expenditure_sum = 0;
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

    Button btn_keep;

    static final String DB_NAME = "moneyManagerDB";
    static final String TB_NAME_0 = "moneyManager_TODAY";
    static final String TB_NAME_1 = "moneyManager_1";
    static final String TB_NAME_2 = "moneyManager_2";
    static final String TB_NAME_3 = "moneyManager_3";
    static final String TB_NAME_4 = "moneyManager_4";
    static final String TB_NAME_5 = "moneyManager_5";
    static final String TB_NAME_6 = "moneyManager_6";
    static final String TB_NAME_7 = "moneyManager_7";
    static final String TB_NAME_8 = "moneyManager_8";
    static final String TB_NAME_9 = "moneyManager_9";
    String TB_NAME_USE = "";
    static final String[] FROM = new String[]{"mark","type","money","note"};
    private static final int REQ_FROM_A = 1;

    float x1;
    float x2;

    SQLiteDatabase db;
    Cursor cur;
    SimpleCursorAdapter adapter;
    SimpleCursorAdapter empty_adapter;
    ListView lv;
    ListView empty_lv;

    Intent add_income;
    Intent add_expenditrue;
    Intent look_chart;
    Bundle bundle;

    //-----------Date variables-------------//
    int year; // store c in String format
    int month;
    int date;
    int showDate;
    Calendar c; // get today's date
    TextView dateTex;
    TextView income;
    TextView expenditure;
    TextView textView2;
    //-----------Date variables-------------//
    //-----------

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_keep = (Button)findViewById(R.id.button_keep);
        btn_keep.setEnabled(false);

        page = (View)findViewById(R.id.layout);
        page.setOnClickListener((View.OnClickListener) this);
        page.setOnTouchListener((View.OnTouchListener) this);

        c = Calendar.getInstance();
        getdate();
        dateTex = (TextView)findViewById(R.id.date_show);
        showDate(i);
        income = (TextView)findViewById(R.id.income);
        expenditure = (TextView)findViewById(R.id.expenditure);
        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE,null);

        textView2 = (TextView)findViewById(R.id.textView2);
        //db.execSQL("delete from "+TB_NAME);

        createTable(TB_NAME_0);
        createTable(TB_NAME_1);
        createTable(TB_NAME_2);
        createTable(TB_NAME_3);
        createTable(TB_NAME_4);
        createTable(TB_NAME_5);
        createTable(TB_NAME_6);
        createTable(TB_NAME_7);
        createTable(TB_NAME_8);
        createTable(TB_NAME_9);

        showData(i);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_B || resultCode == RESULT_C)
        {
            if(requestCode == REQ_FROM_A)
            {
                input_mark = data.getExtras().getString("bun_mark");
                input_type = data.getExtras().getString("bun_type");
                input_money = data.getExtras().getString("bun_money");
                input_note = data.getExtras().getString("bun_note");
                input_positive = data.getExtras().getString("bun_positive");
                input_totalT = data.getExtras().getString("bun_totalT");
            }
        }
        if(!input_mark.equals(""))
            addData(input_mark,input_totalT,input_type,input_money,input_note,input_positive);
        showData(i);
    }

    public void getdate(){ // get today's information
        date = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH)+1;
        year = c.get(Calendar.YEAR);
    }

    public void showDate(int i){
        showDate = date - i;
        dateTex.setText(year+"/"+month+"/"+showDate);
    }

    public void createTable(String TB_NAME){
        String createTable = "CREATE TABLE IF NOT EXISTS " + TB_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+"mark VARCHAR(8),"+"judge VARCHAR(8),"+"type VARCHAR(8),"+"money VARCHAR(8),"+"note VARCHAR(8),"+"positive VARCHAR(8))";
        db.execSQL(createTable);
    }

    public void curRaw(int i_mark){
        switch (i_mark){
            case 0: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_0, null);
                TB_NAME_USE = TB_NAME_0;
                break;
            }
            case 1: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_1, null);
                TB_NAME_USE = TB_NAME_1;
                break;
            }
            case 2: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_2, null);
                TB_NAME_USE = TB_NAME_2;
                break;
            }
            case 3: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_3, null);
                TB_NAME_USE = TB_NAME_3;
                break;
            }
            case 4: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_4, null);
                TB_NAME_USE = TB_NAME_4;
                break;
            }
            case 5: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_5, null);
                TB_NAME_USE = TB_NAME_5;
                break;
            }
            case 6: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_6, null);
                TB_NAME_USE = TB_NAME_6;
                break;
            }
            case 7: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_7, null);
                TB_NAME_USE = TB_NAME_7;
                break;
            }
            case 8: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_8, null);
                TB_NAME_USE = TB_NAME_8;
                break;
            }
            case 9: {
                cur = db.rawQuery("SELECT * FROM " + TB_NAME_9, null);
                TB_NAME_USE = TB_NAME_9;
                break;
            }
        }
    }

    public void add_income(View view){ //botton "add_income"
        add_income = new Intent(MainActivity.this,add_income.class);
        startActivityForResult(add_income,REQ_FROM_A);
    }

    public void add_expenditure(View view){ //botton "add_income"
        add_expenditrue = new Intent(MainActivity.this,add_expenditure.class);
        startActivityForResult(add_expenditrue,REQ_FROM_A);
    }

    public boolean onTouch(View view, MotionEvent motionEvent){
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){ //when I touch my screen
            x1 = motionEvent.getX();
        }else
        if(motionEvent.getAction()==MotionEvent.ACTION_UP){ //when my finger leaves the screen
            x2 = motionEvent.getX();

            if(x1-x2 > 0 && i < 10)  //slide right to left
                i = i + 1;
            if(x1-x2 < 0 && i > 0) //slide left to right
                i = i - 1;

            showDate(i);
            showData(i);
        }
        return false;
    }

    public void showData(int i_mark){
        income_sum = 0;
        expenditure_sum = 0;
        curRaw(i_mark);
        if(cur.moveToFirst()){
            adapter = new SimpleCursorAdapter(this, R.layout.item, cur, FROM, new int[]{R.id.mark, R.id.type, R.id.money, R.id.note}, 0);
            lv = (ListView) findViewById(R.id.lv);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener((AdapterView.OnItemClickListener) this);
            do{
                if(Integer.valueOf(cur.getString(6))==1) //positive
                    income_sum = income_sum + Integer.valueOf(cur.getString(4));
                else if(Integer.valueOf(cur.getString(6))==0)
                    expenditure_sum = expenditure_sum + Integer.valueOf(cur.getString(4));
            }while (cur.moveToNext());
        }
        else{
            income_sum = 0;
            expenditure_sum = 0;
            empty_lv = (ListView)findViewById(R.id.lv);
            empty_lv.setAdapter(empty_adapter);
        }
        income.setText(""+income_sum);
        expenditure.setText(""+expenditure_sum);
    }

    @Override
    public void onClick(View view) {
    }

    public boolean onLongClick(View view){
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        curRaw(i);
        cur.moveToPosition(position);
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.delete_Q))
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.delete(TB_NAME_USE,"_id="+cur.getInt(0),null);
                        showData(i);
                    }
                })
                .setNegativeButton(getResources().getString(R.string.no),null)
                .setCancelable(false)
                .show();
    }

    public void addData(String mark,String judge,String type,String money,String note,String positive){
        ContentValues cv = new ContentValues(6);
        cv.put("mark",mark);
        cv.put("judge",judge);
        cv.put("type",type);
        cv.put("money",money);
        cv.put("note",note);
        cv.put("positive",positive);
        switch (i){
            case 0:
                db.insert(TB_NAME_0,null,cv);
                break;
            case 1:
                db.insert(TB_NAME_1,null,cv);
                break;
            case 2:
                db.insert(TB_NAME_2,null,cv);
                break;
            case 3:
                db.insert(TB_NAME_3,null,cv);
                break;
            case 4:
                db.insert(TB_NAME_4,null,cv);
                break;
            case 5:
                db.insert(TB_NAME_5,null,cv);
                break;
            case 6:
                db.insert(TB_NAME_6,null,cv);
                break;
            case 7:
                db.insert(TB_NAME_7,null,cv);
                break;
            case 8:
                db.insert(TB_NAME_8,null,cv);
                break;
            case 9:
                db.insert(TB_NAME_9,null,cv);
                break;
        }
    }

    public void chart(View view){
        salary = 0;
        pocket = 0;
        lucky = 0;
        others_income = 0;
        food = 0;
        drinks = 0;
        transport = 0;
        medical = 0;
        enter = 0;
        others_expend = 0;

        curRaw(i);
        cur.moveToFirst();
        do{
            if(Integer.valueOf(cur.getString(2))==1){ //收入，薪水
                salary = salary + Integer.valueOf(cur.getString(4));
            }
            else if(Integer.valueOf(cur.getString(2))==2){ //收入，零用錢
                pocket = pocket + Integer.valueOf(cur.getString(4));
            }
            else if(Integer.valueOf(cur.getString(2))==3){ //收入，撿到的
                lucky = lucky + Integer.valueOf(cur.getString(4));
            }
            else if(Integer.valueOf(cur.getString(2))==4){ //收入，其他
                others_income = others_income + Integer.valueOf(cur.getString(4));
            }
            else if(Integer.valueOf(cur.getString(2))==5){ //支出，食物
                food = food + Integer.valueOf(cur.getString(4));
            }
            else if(Integer.valueOf(cur.getString(2))==6){ //支出，飲料
                drinks = drinks + Integer.valueOf(cur.getString(4));
            }
            else if(Integer.valueOf(cur.getString(2))==7){ //支出，交通
                transport = transport + Integer.valueOf(cur.getString(4));
            }
            else if(Integer.valueOf(cur.getString(2))==8){ //支出，醫療
                medical = medical + Integer.valueOf(cur.getString(4));
            }
            else if(Integer.valueOf(cur.getString(2))==9){ //支出，娛樂
                enter = enter + Integer.valueOf(cur.getString(4));
            }
            else if(Integer.valueOf(cur.getString(2))==10){ //支出，其他
                others_expend = others_expend + Integer.valueOf(cur.getString(4));
            }
        }while(cur.moveToNext());

        look_chart = new Intent(MainActivity.this,look_chart.class);
        bundle = new Bundle();
        bundle.putInt("bun_1",salary);
        bundle.putInt("bun_2",pocket);
        bundle.putInt("bun_3",lucky);
        bundle.putInt("bun_4",others_income);
        bundle.putInt("bun_5",food);
        bundle.putInt("bun_6",drinks);
        bundle.putInt("bun_7",transport);
        bundle.putInt("bun_8",medical);
        bundle.putInt("bun_9",enter);
        bundle.putInt("bun_10",others_expend);
        look_chart.putExtras(bundle);
        startActivity(look_chart);
    }
}