package com.example.happy.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private DatePicker dpResult;
    private Button btnChangeDate;

    private int month;
    private int year;
    private int day;

    static final int DATE_DIALOG_ID = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentDateOnView();
        addListenerOnButton();
    }

    public void setCurrentDateOnView(){
        tvDisplay = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        //set current date to text view
        tvDisplay.setText(new StringBuilder().append(month+1).append("-")
                            .append(day).append("-").append(year).append("-"));

        //set the current date to the date picker
        dpResult.init(year,month,day,null);
    }

    public void addListenerOnButton(){
        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                //Set time picker as current date
                return new DatePickerDialog(this,datePickerListener,year,month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view,int selectedYear,int selectedMonth,int selectedDay){
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            tvDisplay.setText(new StringBuilder().append(month+1).append("-").append(day).append("-")
                    .append(year).append(" "));
            dpResult.init(year,month,day,null);
        }
    };
}
