package com.example.project8;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Interview extends Fragment {
    private Button addButton;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.interview,container,false);

        listView = (ListView) view.findViewById(R.id.list_item);
        addButton = (Button) view.findViewById(R.id.addExperience);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddExperience.class));
            }
        });

        final ArrayList<String> company = new ArrayList<String>();
        company.add("Google");
        company.add("Microsoft");
        company.add("Amazon");
        company.add("Salesforce");
        company.add("Samsung");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.interview_button, R.id.text, company);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                Intent intent = new Intent(getActivity(), Experience.class);
                intent.putExtra("Company", company.get(position));
                startActivity(intent);
            }
        });

        return view;
    }
}
