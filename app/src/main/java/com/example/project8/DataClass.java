package com.example.project8;

import java.util.ArrayList;
import java.util.List;

public class DataClass {
    public String name;
    public String suggestion;
    public String coding_round;
    public String interview_3;
    public String interview_1;
    public String interview_2;

    DataClass(String name, String coding_round, String interview_1, String interview_2, String interview_3, String suggestion){
        this.name = name;
        this.suggestion = suggestion;
        this.coding_round = coding_round;
        this.interview_1 = interview_1;
        this.interview_2 = interview_2;
        this.interview_3 = interview_3;
    }
}
