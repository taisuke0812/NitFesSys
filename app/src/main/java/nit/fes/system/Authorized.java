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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class Authorized extends AppCompatActivity {

    private List<String> id_data = new ArrayList<String>();
    private List<String> pass_data = new ArrayList<String>();
    private String id;
    private String pass;
    public int Case;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(nit.fes.system.R.layout.activity_authorized);

        Intent i = getIntent();

        if(i.getStringExtra("id") != null) {
           setId(i.getStringExtra("id"));
        }
        if(i.getStringExtra("pass") != null) {
            setPass(i.getStringExtra("pass"));
        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("register");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String regex = "\\[(.+?)\\]";
                String regex2 = "\\[[.+?]\\]";
                Map<String, Object> map = new HashMap<>();
                map.put("id",dataSnapshot.getValue(Object.class));
                String text = map.get("id").toString();
                setId_data(matching(regex,text));

                map.put("pass",dataSnapshot.getValue(Object.class));
                String text_ = map.get("pass").toString();
                setPass_data(matching(regex2,text_));
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        Case = Match(this.getId(),this.getPass());
        Intent intent = new Intent(getApplication(), Main2Activity.class);
        if(Case == 0){
            //startActivity(intent);
        }
        if(Case == 2){
            startActivity(intent);
        }
    }

    public void setId_data(List<String> id){
        this.id_data = id;
    }
    public void setPass_data(List<String> pass){
        this.pass_data = pass;
    }
    public void setId(String id_){this.id = id_;}
    public void setPass(String pass_){this.pass = pass_;}

    public List<String> getId_data(){
        return this.id_data;
    }
    public List<String> getPass_data(){
        return this.pass_data;
    }
    public String getId(){return this.id;}
    public String getPass(){return this.pass;}



    public List<String> matching(String regex, String target) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        StringBuilder buff = new StringBuilder();
        List<String> array = new ArrayList<String>();
        while(matcher.find()) {
            array.add(matcher.group(1));
        }
        return array;
    }


    public int Match(String id, String pass) {

        int sum = 0;

        for (String string : this.getId_data()) {
            if (string.equals(id)) {
                sum = sum + 1;
            }
        }

        for (String string_ : this.getPass_data()) {
            if (string_.equals(pass)) {
                sum = sum + 1;
            }
        }

        return sum;
    }
}

