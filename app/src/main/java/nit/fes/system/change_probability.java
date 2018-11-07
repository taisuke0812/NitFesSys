package nit.fes.system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class change_probability extends AppCompatActivity {
    private String name;
    private int key;
    private String pass;
    private String pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_probability);
        String id_name = "001";
        String id_pro;
        Intent i = getIntent();
        if(i.getStringExtra("NAME") != null) {
            id_name = i.getStringExtra("NAME");
            setName(id_name);
        }
        int key_intent;
        key_intent = i.getIntExtra("KEY",0);
        setKey(key_intent);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("register/" + getName());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Intent back = new Intent(getApplication(), MainActivity.class);
                Map<String, Object> something = (Map<String, Object>) dataSnapshot.getValue();
                String correct_pro = something.get("pro").toString();
                TextView text = (TextView)findViewById(R.id.probability);
                text.setText(correct_pro);
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
            }

        });

        findViewById(nit.fes.system.R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), home_1.class);
                intent.putExtra("NAME",getName());
                intent.putExtra("KEY",getKey());
                startActivity(intent);
            }
        });

        findViewById(nit.fes.system.R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input_pro = findViewById(R.id.input_pro);
                EditText input_pass = findViewById(R.id.input_pass);
                String probability = "";
                String password = "";
                if(input_pro.getText() != null) {
                    SpannableStringBuilder ssb = (SpannableStringBuilder) input_pro.getText();
                    probability = ssb.toString();
                    setPro(probability);
                    if (input_pass.getText() != null) {
                        SpannableStringBuilder ssb_ = (SpannableStringBuilder) input_pro.getText();
                        password = ssb_.toString();
                        setPass(password);

                        if(getPass()=="" || getName()==""){
                            Intent intent_ = new Intent(getApplication(), home_1.class);
                            intent_.putExtra("NAME", getName());
                            intent_.putExtra("KEY", getKey());
                            startActivity(intent_);

                        }else {
                            reg data = new reg(getName(), getPass(), getPro());
                            DatabaseReference dataref_ = FirebaseDatabase.getInstance().getReference("register");
                            String key = getName();
                            Map<String, Object> map = new HashMap<>();
                            map.put(key, data.toMap());
                            dataref_.updateChildren(map);

                            Intent intent = new Intent(getApplication(), home_1.class);
                            intent.putExtra("NAME", getName());
                            intent.putExtra("KEY", getKey());
                            startActivity(intent);
                        }
                    }
                }
            }
        });








    }
    public void sendData(reg _data) {
        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference("register");
        String key = getName();
        Map<String, Object> map = new HashMap<>();
        map.put(key, _data.toMap());
        dataref.updateChildren(map);
    }

    public void setKey(int intent_key){
        this.key = intent_key;
    }
    public int  getKey(){
        return this.key;
    }
    public void setName(String intent_text){
        this.name = intent_text;
    }
    public String getName(){
        return this.name;
    }
    public void setPass(String pass_){this.pass = pass_;}
    public String getPass(){return this.pass;}
    public void setPro(String pro_){this.pro = pro_;}
    public String getPro(){return this.pro;}
}
