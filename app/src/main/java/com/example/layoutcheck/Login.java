package com.example.layoutcheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    private TextView signUpTextview;
    private TextView forgotSecurityPINTextview;
    private Button Submit;
    EditText passwordL;
    boolean passwordResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        signUpTextview=findViewById(R.id.SignUpText);
        signUpTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        forgotSecurityPINTextview=findViewById(R.id.forgotSecurityPINTextView);
        forgotSecurityPINTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotSecurityPIN.class);
                startActivity(intent);
            }
        });
        Submit=findViewById(R.id.LoginSubmitBtn);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, HomePage.class);
                startActivity(intent);
            }
        });


        //Password Visibility for eye drawable
        passwordL=findViewById(R.id.Security_PINL);
        passwordL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=passwordL.getRight()-passwordL.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=passwordL.getSelectionEnd();
                        if(passwordResult){
                            //set drawable image
                            passwordL.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            //for hide password
                            passwordL.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordResult=false;
                        }
                        else {
                            //set drawable image
                            passwordL.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            //for show password
                            passwordL.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordResult=true;
                        }
                        passwordL.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
    }
}