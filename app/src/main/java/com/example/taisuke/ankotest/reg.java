package com.example.taisuke.ankotest;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class reg {
    private String id;
    private String pass;

    public reg(String id,String pass){
        this.id = id;
        this.pass = pass;
    }
    public String getId(){
        return id;
    }

    public String getPass(){ return pass;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setPass(String pass){
        this.pass = pass;
    }

    @Exclude
    public Map<String,String> toMap(){
        HashMap<String,String> hashmap = new HashMap();
        hashmap.put("id",id);
        hashmap.put("pass",pass);
        return hashmap;
    }
}


