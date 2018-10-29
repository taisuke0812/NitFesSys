package com.example.taisuke.ankotest;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class data {
    private double count;
    private String time;

    public data(double count,String time){
        this.count = count;
        this.time = time;
    }
    public double getCount(){
        return count;
    }

    public String getTime(){
        return time;
    }

    public void setCount(double count){
        this.count = count;
    }

    public void setTime(String time){
        this.time = time;
    }

    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String,Object> hashmap = new HashMap();
        hashmap.put("time",time);
        hashmap.put("count",count);
        return hashmap;
    }
}


