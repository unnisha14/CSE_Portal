package com.example.project8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Notes extends Fragment {
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes,container,false);

        listView = (ListView) view.findViewById(R.id.list);

        final ArrayList<String> sem_list = new ArrayList<String>();
        sem_list.add("Semester 1");
        sem_list.add("Semester 2");
        sem_list.add("Semester 3");
        sem_list.add("Semester 4");
        sem_list.add("Semester 5");
        sem_list.add("Semester 6");
        sem_list.add("Semester 7");
        sem_list.add("Semester 8");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.interview_button, R.id.text, sem_list);
        listView.setAdapter(adapter);



        return view;
    }
}
