package nit.fes.system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class button_activity extends AppCompatActivity {
    private String name = "error";
    private int key = 0;
    private int count;
    private String __text;//for test
    private int num;
    private int kind;
    private double pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id_name;
        String id_pro;
        int key_intent;
        Intent i = getIntent();
        if(i.getStringExtra("NAME") != null) {
            id_name = i.getStringExtra("NAME");
            setName(id_name);
        }

        int c_ = i.getIntExtra("count",2);
        setCount(c_);

        int shop_num = i.getIntExtra("num",0);
        setNum(shop_num);

        int kind_ = i.getIntExtra("kind",0);
        setKind(kind_);


        Date date = new Date();
        final String date_data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        //key_intent = i.getIntExtra("KEY",0);
        //setKey(key_intent);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(getName() + "/" + String.valueOf(getKind()) + "/" +date_data.substring(8,10) + "/" + date_data.substring(11,13) + "/key");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Object> something = (Map<String,Object>)dataSnapshot.getValue();
                if(something != null) {
                    double sending = Double.parseDouble(something.get("count").toString()) + 1.0;

                    int count__ = (int) sending;
                    setKey(count__);
                }
                data send_data = new data(getCount(), date_data );
                //setKey(getKey() + 1);
                data send_key_count = new data(getKey(),date_data);
                data send_test = new data (1,get__text());
                sendData(send_data,date_data);

                sendData_(send_key_count,date_data);
                sendData__(send_test);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef_ = database.getReference("register/" + getName());
                myRef_.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Map<String, Object> something = (Map<String, Object>) dataSnapshot.getValue();
                        if (something != null) {
                            double prob = Double.parseDouble(something.get("pro").toString());
                            Intent intent = new Intent(getApplication(), show_image.class);
                            intent.putExtra("NAME",getName());
                            intent.putExtra("KEY",getKey());
                            intent.putExtra("pro",0.2);
                            Intent _intent = new Intent(getApplication(), show_image_h.class);
                            _intent.putExtra("NAME",getName());
                            _intent.putExtra("KEY",getKey());
                            _intent.putExtra("pro",0.2);

                            Random rand = new Random();
                            int sum = 0;
                            sum = rand.nextInt(101);
                            double pro_count = 100*prob;
                            if((double)sum < pro_count) {
                                startActivity(intent);
                            }else{
                                startActivity(_intent);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(final DatabaseError databaseError) {
                    }

                });

            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
            }

        });

    }

    public void sendData(data _data,String date) {
        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference(getName() + "/"+ String.valueOf(getKind()) + "/" +date.substring(8,10) + "/" + date.substring(11,13));
        String key =  String.valueOf(this.getKey());
        Map<String, Object> map = new HashMap<>();
        map.put(key, _data.toMap());
        dataref.updateChildren(map);
    }
    public void sendData_(data _data,String date) {
        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference(getName() + "/"+ String.valueOf(getKind()) + "/" + date.substring(8,10) + "/" + date.substring(11,13));
        String key = "key";
        Map<String, Object> map = new HashMap<>();
        map.put(key, _data.toMap());
        dataref.updateChildren(map);
    }

    public void sendData__(data _data) {
        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference(getName());
        String key = "test";
        Map<String, Object> map = new HashMap<>();
        map.put(key, _data.toMap());
        dataref.updateChildren(map);
    }

    public void setName(String intent_name){
        this.name = intent_name;
    }
    public String getName(){ return this.name; }

    public void setKey(int intent_key){
        this.key = intent_key;
    }
    public int  getKey(){
        return this.key;
    }
    public String get__text(){return this.__text;}
    public void set__text(String text){this.__text = text;}

    public void setNum(int _num){this.num = _num;}
    public int getNum(){return this.num;}

    public void setCount(int _count){this.count = _count;}
    public int getCount(){return this.count;}

    public void setKind(int kind_){
        this.kind = kind_;
    }
    public int getKind(){
        return this.kind;
    }
    public void setPro(double pro_){this.pro = pro_;}
    public double getPro(){return this.pro;}
}
