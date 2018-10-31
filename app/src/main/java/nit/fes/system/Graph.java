
package nit.fes.system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph extends AppCompatActivity {
    private static final String TAG = "" ;
    private DatabaseReference mDatabase;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int num = 1;
        super.onCreate(savedInstanceState);
        setContentView(nit.fes.system.R.layout.activity_graph);
        String id_name;
        Intent i = getIntent();
        if(i.getStringExtra("NAME") != null) {
            id_name = i.getStringExtra("NAME");
            setName(id_name);
        }



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(getName());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String regex = "\\[(.+?)\\]";
                Map<String, Object> map = new HashMap<>();
                map.put("time",dataSnapshot.getValue(Object.class));
                String text = map.get("time").toString();
                String text_data = matching(regex,text);
                setText(text_data);
                Log.d(TAG, "Value is: " + text);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        if(i.getStringExtra("DATA") != null) {
            String text = i.getStringExtra("DATA");
            i.getIntExtra("Count" ,num);
            if (num == 1) {
                //TextView textView1 = findViewById(R.id.textview10);
                //textView1.setText(text);
                }
                if (num == 2) {
                   //TextView textView2 = findViewById(R.id.textview2);
                   //textView2.setText(text);
                }

        }
        findViewById(nit.fes.system.R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Main2Activity.class);
                intent.putExtra("NAME",getName());
                startActivity(intent);
            }
        });


    }

    void setText(String text){
        TextView textview1= findViewById(nit.fes.system.R.id.textview1);
        textview1.setMovementMethod(ScrollingMovementMethod.getInstance());
        textview1.setText(text);
    }

    String matching(String regex, String target) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        StringBuilder buff = new StringBuilder();
        String uriage = "時間 : ";
        String kosuu = "個数 : 1 \n";
        String space = "  ";
        //String[] list = new String[100];
        List<String> array = new ArrayList<String>();
        /*if (matcher.find()) {
            return matcher.group();
        } else {
            throw new IllegalStateException("No match found.");
        }*/
        /*
        int i = 0;
        int j = 0;
        int k = 0;
        for(j = 0;j < 100;j++){
            list[j] = "";
        }
        while(matcher.find()){
            //buff.append(uriage + matcher.group(1) + space + kosuu);
            list[i] = matcher.group(1);
            i++;
        }

        Arrays.sort(list);
        for(k = 0;k < 100;k++){
            buff.append(uriage + list[k].toString() + space + kosuu);
        }*/
        while(matcher.find()) {
            array.add(matcher.group(1));
        }

        Collections.sort(array,Collections.<String>reverseOrder());
        for(String string: array){
            buff.append(uriage + string + space + kosuu);
        }
        return buff.toString();
    }
    public void setName(String intent_name){
        this.name = intent_name;
    }

    public String getName(){
        return this.name;
    }

}
