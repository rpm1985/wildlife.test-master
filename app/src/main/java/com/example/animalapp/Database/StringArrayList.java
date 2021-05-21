package com.example.animalapp.Database;

import java.util.ArrayList;
import java.util.List;


public class StringArrayList {
    private ArrayList<String> stringArrayList = new ArrayList<>();

    public StringArrayList(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }

    public void setStringArrayList(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }
}

