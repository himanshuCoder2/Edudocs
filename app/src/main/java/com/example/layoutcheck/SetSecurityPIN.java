package com.example.layoutcheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SetSecurityPIN extends AppCompatActivity {
private Button SetSubmitBtn;
    EditText passwordS;
    boolean passwordResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_set_security_pin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SetSubmitBtn=findViewById(R.id.SetSecuritySubmitBtn);
        SetSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetSecurityPIN.this, Login.class);
                startActivity(intent);
            }
        });


        //Password Visibility for eye drawable
        passwordS=findViewById(R.id.Security_PINF);
        passwordS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=passwordS.getRight()-passwordS.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=passwordS.getSelectionEnd();
                        if(passwordResult){
                            //set drawable image
                            passwordS.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            //for hide password
                            passwordS.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordResult=false;
                        }
                        else {
                            //set drawable image
                            passwordS.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            //for show password
                            passwordS.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordResult=true;
                        }
                        passwordS.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
    }
}