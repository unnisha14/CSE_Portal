package com.example.project8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterClass extends ArrayAdapter<DataClass> {
    public AdapterClass(@NonNull Context context , @NonNull List<DataClass> objects) {
        super(context , 0 , objects);
    }

    @NonNull
    @Override
    public View getView(int position , @Nullable View v , @NonNull ViewGroup parent) {
        View convertView = v;
        if(v == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.details, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView sugg = (TextView) convertView.findViewById(R.id.sugg);
        TextView coding = (TextView) convertView.findViewById(R.id.coding);
        TextView interview_1 = (TextView) convertView.findViewById(R.id.int_1);
        TextView interview_2 = (TextView) convertView.findViewById(R.id.int_2);
        TextView interview_3 = (TextView) convertView.findViewById(R.id.int_3);

        DataClass curr = getItem(position);
        name.setText(curr.name);
        sugg.setText(curr.suggestion);
        coding.setText(curr.coding_round);
        interview_1.setText(curr.interview_1);
        interview_2.setText(curr.interview_2);
        interview_3.setText(curr.interview_3);


        return convertView;
    }
}