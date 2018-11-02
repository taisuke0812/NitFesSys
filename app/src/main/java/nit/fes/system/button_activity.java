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

public class button_activity extends AppCompatActivity {
    private String name;
    private int key;
    private String __text;//for test
    private int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id_name;
        int key_intent;
        Intent i = getIntent();
        if(i.getStringExtra("NAME") != null) {
            id_name = i.getStringExtra("NAME");
            setName(id_name);
        }
        /*
        key_intent = i.getIntExtra("KEY",0);
        setKey(key_intent);
        */
        int shop_num = i.getIntExtra("Num",0);
        setNum(shop_num);

        Date date = new Date();
        String date_data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(getName() + "/" + String.valueOf(getNum()) + "/" + date_data.substring(11,13) +"/key");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data something = dataSnapshot.getValue(data.class);
                double get_count = dataSnapshot.child("count").getValue(double.class);
                //String get_time = something.getTime();
                //double get_count = something.getCount();
                int count = (int)get_count;
                setKey(count);
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
            }
        });
        */
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(getName() + "/key");
        myRef.addValueEventListener(new ValueEventListener() {
            private static final String TAG ="" ;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                /*
                data __data = dataSnapshot.getValue(data.class);
                double i = __data.getCount();
                setKey((int)i);

                Map<String, Object> map = new HashMap<>();
                map.put("count",dataSnapshot.getValue(Object.class));
                String get_key = map.get("count").toString();
                set__text(get_key);
                //setKey(Integer.valueOf(get_key));
                //Log.d(TAG, "Value is: " + get_key);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    */



        key_intent = i.getIntExtra("KEY",0);
        setKey(key_intent);



        data send_data = new data(1, date_data );
        setKey(getKey() + 1);
        data send_key_count = new data(getKey(),date_data);
        data send_test = new data (1,get__text());
        sendData(send_data,date_data);
        sendData_(send_key_count);
        sendData__(send_test);

        Intent intent = new Intent(getApplication(), show_image.class);
        //intent.putExtra("DATA", barcodeResult.getText());
        intent.putExtra("NAME",getName());
        intent.putExtra("KEY",getKey());
        startActivity(intent);
    }

    public void sendData(data _data,String date) {
        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference(getName());
        String key = String.valueOf(getNum()) + "/" +date.substring(8,19) + "/" + date.substring(11,13) + "/" + String.valueOf(this.getKey());
        Map<String, Object> map = new HashMap<>();
        map.put(key, _data.toMap());
        dataref.updateChildren(map);
    }
    public void sendData_(data _data) {
        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference(getName());
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
}
