package com.example.project8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity {
    private EditText username, email, password, phoneNumber, rollNo;
    private Spinner branchSpinner, semSpinner;
    private Button reg_btn;
    private String branch;
    private int sem;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        rollNo = (EditText) findViewById(R.id.roll_no);
        branchSpinner = (Spinner) findViewById(R.id.branch);
        semSpinner = (Spinner) findViewById(R.id.semester);
        reg_btn = (Button) findViewById(R.id.reg);
        setBranchSpinner();
        setSemesterSpinner();

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetails userDetails = new UserDetails(username.getText().toString(),
                                                          email.getText().toString(),
                                                          phoneNumber.getText().toString(),
                                                          Integer.parseInt(rollNo.getText().toString()),
                                                          branch,
                                                          sem);

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(CreateActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String uid = firebaseAuth.getCurrentUser().getUid();
                                DatabaseReference ref = databaseReference.child(uid);
                                ref.setValue(userDetails)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(CreateActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(CreateActivity.this, Homepage.class));
                                                finish();
                                            }
                                        });
                            }
                        });
            }
        });
    }

    private void setBranchSpinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(CreateActivity.this, R.array.Branch, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        branchSpinner.setAdapter(genderSpinnerAdapter);

        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent , View view , int position , long id) {
                String selection = (String) parent.getItemAtPosition(position);
                branch = selection;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                branch = "Unknown";
            }
        });
    }

    private void setSemesterSpinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(CreateActivity.this, R.array.Semester, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        semSpinner.setAdapter(genderSpinnerAdapter);

        semSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent , View view , int position , long id) {
                String selection = (String) parent.getItemAtPosition(position);

                int temp = Integer.parseInt(selection);
                if (temp > 0){
                    sem = temp;
                }else{
                    sem = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sem = 0;
            }
        });
    }
}