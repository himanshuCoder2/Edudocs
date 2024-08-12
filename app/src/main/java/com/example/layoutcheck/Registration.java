package com.example.layoutcheck;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Calendar;
import android.widget.NumberPicker;


public class Registration extends AppCompatActivity {
    private TextView fullnameTextView;
    private TextView enrolmentIdTextView;
    EditText passwordR;
    boolean passwordResult;
    EditText enterOTP;
    private EditText startDateEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fullnameTextView = findViewById(R.id.fullname_textview);
        enrolmentIdTextView = findViewById(R.id.enrolment_no_textview);

        Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        String enrolmentId = intent.getStringExtra("enrolment_id");

        fullnameTextView.setText(fullname);
        enrolmentIdTextView.setText(enrolmentId);


        //Password Visibility for eye drawable
        passwordR=findViewById(R.id.Security_PIN);
        passwordR.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=passwordR.getRight()-passwordR.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=passwordR.getSelectionEnd();
                        if(passwordResult){
                            //set drawable image
                            passwordR.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            //for hide password
                            passwordR.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordResult=false;
                        }
                        else {
                            //set drawable image
                            passwordR.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            //for show password
                            passwordR.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordResult=true;
                        }
                        passwordR.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        //for otp eye view
        enterOTP=findViewById(R.id.OTP);
        enterOTP.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=enterOTP.getRight()-enterOTP.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=enterOTP.getSelectionEnd();
                        if(passwordResult){
                            //set drawable image
                            enterOTP.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            //for hide password
                            enterOTP.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordResult=false;
                        }
                        else {
                            //set drawable image
                            enterOTP.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            //for show password
                            enterOTP.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordResult=true;
                        }
                        enterOTP.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        startDateEditText = findViewById(R.id.Course_Start_Date);
        startDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMonthYearPickerDialog();
            }
        });
    }
    private void showMonthYearPickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.month_year_picker, null);
        builder.setView(dialogView);


        final NumberPicker monthPicker = dialogView.findViewById(R.id.monthPicker);
        final NumberPicker yearPicker = dialogView.findViewById(R.id.yearPicker);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(Calendar.getInstance().get(Calendar.MONTH) + 1);

        yearPicker.setMinValue(Calendar.getInstance().get(Calendar.YEAR) - 10);
        yearPicker.setMaxValue(Calendar.getInstance().get(Calendar.YEAR) + 10);
        yearPicker.setValue(Calendar.getInstance().get(Calendar.YEAR));

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedMonth = monthPicker.getValue();
                int selectedYear = yearPicker.getValue();
                String date = selectedYear + "-" + selectedMonth;
                startDateEditText.setText(date);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
