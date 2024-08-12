package com.example.layoutcheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RigistrationT extends AppCompatActivity {
    private TextView fullnameTextView;
    private TextView enrolmentIdTextView;
    EditText password;

    boolean passwordResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rigistration_t);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fullnameTextView = findViewById(R.id.fullname_textview2);
        enrolmentIdTextView = findViewById(R.id.enrolment_no_textview2);

        Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        String enrolmentId = intent.getStringExtra("enrolment_id");

        fullnameTextView.setText(fullname);
        enrolmentIdTextView.setText(enrolmentId);

        //Password Visibility for eye drawable
        password=findViewById(R.id.Security_PIN);
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=password.getSelectionEnd();
                        if(passwordResult){
                            //set drawable image
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            //for hide password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordResult=false;
                        }
                        else {
                            //set drawable image
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            //for show password
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordResult=true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });



    }
}