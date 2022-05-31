package com.example.project8;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public int getCount() {
        return 3;
    }


    public Fragment getItem(int i) {
        switch (i){
            case 0: return new Notes();
            case 1: return new Interview();
            case 2: return new Courses();
        };
        return null;
    }

    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return ("Notes");
            case 1: return ("Interview Prep");
            case 2: return ("Courses");
        };
        return null;
    }
}