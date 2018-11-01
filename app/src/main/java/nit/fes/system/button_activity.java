package nit.fes.system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class button_activity extends AppCompatActivity {
    private String name;
    private int key;
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

            key_intent = i.getIntExtra("KEY",0);
            setKey(key_intent);
        Date date = new Date();
        String date_data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        data send_data = new data(1,"[" + date_data + "]");
        sendData(send_data);

        Intent intent = new Intent(getApplication(), show_image.class);
        //intent.putExtra("DATA", barcodeResult.getText());
        intent.putExtra("NAME",getName());
        startActivity(intent);
    }

    public void sendData(data _data) {
        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference(getName());
        String key = dataref.push().getKey();
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


}
