package com.example.project8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddExperience extends AppCompatActivity {
    private EditText companyName, suggestion, coding_round, interview_1, interview_2, interview_3;
    private Button done_btn, add_btn;
    private LinearLayout layout;

    private String descList;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference user_root, root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experience);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        String uid = user.getUid();
        user_root = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        root = FirebaseDatabase.getInstance().getReference();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);

        descList = new String();

        companyName = (EditText) findViewById(R.id.company);
        suggestion = (EditText) findViewById(R.id.sugg);
        coding_round = (EditText) findViewById(R.id.coding);
        interview_1 = (EditText) findViewById(R.id.int_1);
        interview_2 = (EditText) findViewById(R.id.int_2);
        interview_3 = (EditText) findViewById(R.id.int_3);
        layout = (LinearLayout) findViewById(R.id.layout);
        done_btn = (Button) findViewById(R.id.done);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String company_name = companyName.getText().toString();
                String sugg = suggestion.getText().toString();
                DatabaseReference df = user_root.child(company_name);
                //InterviewAdd list = new InterviewAdd(company_name, sugg, descList);
                df.child("Coding Round").setValue(coding_round.getText().toString());
                df.child("Interview 1").setValue(interview_1.getText().toString());
                df.child("Interview 2").setValue(interview_2.getText().toString());
                df.child("Interview 3").setValue(interview_3.getText().toString());
                df.child("Suggestion").setValue(sugg).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        DatabaseReference df1 = root.child("Company");
                        df1.child(company_name).setValue(uid).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(AddExperience.this, Interview.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
            }
        });
    }
}