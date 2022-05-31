package com.example.project8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Experience extends AppCompatActivity {
    private ListView listView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);

        Intent intent = getIntent();
        String company_name = intent.getStringExtra("Company");
        listView = (ListView) findViewById(R.id.list_view);

        List<DataClass> list = new ArrayList<DataClass>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> items = snapshot.getChildren().iterator();
                while (items.hasNext()){
                    DataSnapshot it = items.next();
                    if (it.hasChild(company_name)){
                        String name, suggestion, coding_round;
                        String interview_1, interview_2, interview_3;
                        name = it.child("username").getValue().toString();
                        suggestion = it.child(company_name).child("Suggestion").getValue().toString();
                        coding_round = it.child(company_name).child("Coding Round").getValue().toString();
                        interview_1 = (String) it.child(company_name).child("Interview 1").getValue();
                        interview_2 = (String) it.child(company_name).child("Interview 2").getValue();
                        interview_3 = (String) it.child(company_name).child("Interview 3").getValue();
                        DataClass data = new DataClass(name, coding_round, interview_1, interview_2, interview_3, suggestion);
                        list.add(data);
                    }
                }

                /*for (int i = 0;i < list.size(); i++) {
                    Toast.makeText(Experience.this, list.get(i).name +
                            list.get(i).coding_round + list.get(i).interview_1 +
                            list.get(i).interview_2, Toast.LENGTH_SHORT).show();
                }*/

                //Toast.makeText(Experience.this, "ok " + list.get(0).name, Toast.LENGTH_SHORT).show();
                AdapterClass adapterClass = new AdapterClass(Experience.this, list);
                listView.setAdapter(adapterClass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}