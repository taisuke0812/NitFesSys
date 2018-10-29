package com.example.taisuke.ankotest;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
           }
        });

        findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);

                EditText input_id_form = findViewById(R.id.input_id);//IDを取得
                SpannableStringBuilder id_data = (SpannableStringBuilder)input_id_form.getText();
                String id = id_data.toString();

                EditText input_pass_form = findViewById(R.id.input_pass);//passを取得
                SpannableStringBuilder pass_data = (SpannableStringBuilder)input_pass_form.getText();
                String pass = pass_data.toString();

                EditText input_token_form = findViewById(R.id.input_token);//tokenを取得
                SpannableStringBuilder token_data = (SpannableStringBuilder)input_token_form.getText();
                String token = token_data.toString();
                //重要
                //token == 97BFC88067とする。
                //2018.10.26作成
                if(token.equals("97BFC88067")){

                    reg reg_data = new reg("(" + id + ")","[" + pass + "]");
                    sendData(reg_data);

                    startActivity(intent);
                }else{



                }




            }
        });
    }



    public void sendData(reg _data) {
        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference("register");
        String key = dataref.push().getKey();
        Map<String, Object> map = new HashMap<>();
        map.put(key, _data.toMap());
        dataref.updateChildren(map);
    }
}
