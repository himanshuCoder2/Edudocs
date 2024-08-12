package com.example.layoutcheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText fullnameEditText;
    private EditText enrolmentIdEditText;
    private Button nextButton;
    private TextView signInText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fullnameEditText = findViewById(R.id.Fullname);
        enrolmentIdEditText = findViewById(R.id.Enrolment_no);
        nextButton = findViewById(R.id.Next);
        signInText = findViewById(R.id.SignInText);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = fullnameEditText.getText().toString();
                String enrolmentId = enrolmentIdEditText.getText().toString();

                if (!fullname.isEmpty() &&!enrolmentId.isEmpty()) {
                    if (enrolmentId.matches("^[A-Za-z]{2}\\d{4}$")) {  //set pattern for student login
                        Intent intent = new Intent(MainActivity.this, Registration.class);
                        intent.putExtra("fullname", fullname);
                        intent.putExtra("enrolment_id", enrolmentId);
                        startActivity(intent);
                    } else if (enrolmentId.matches("^[A-Za-z]{3}\\d{4}$")) {  //set pattern for teacher login
                        Intent intent = new Intent(MainActivity.this, RigistrationT.class);
                        intent.putExtra("fullname", fullname);
                        intent.putExtra("enrolment_id", enrolmentId);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid enrolment ID", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });




        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });







        }
    }

