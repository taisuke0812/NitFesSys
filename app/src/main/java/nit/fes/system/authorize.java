package nit.fes.system;

import android.util.Log;

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

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class authorize {

    private List<String> id_data;
    private List<String> pass_data;


    public void authorize(){
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



    }

    public void setId_data(List<String> id){
        this.id_data = id;
    }
    public void setPass_data(List<String> pass){
        this.pass_data = pass;
    }

    public List<String> getId_data(){
        return this.id_data;
    }

    public List<String> getPass_data(){
        return this.pass_data;
    }


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
        List<String> tmp_id = new ArrayList<String>();
        List<String> tmp_pass= new ArrayList<String>();
        tmp_id = getId_data();
        tmp_pass = getPass_data();
        int sum = 0;
        /*
        for (String string : tmp_id) {
            if (id.equals(string)) {
                sum = sum + 1;
            }
        }

        for (String string_ : tmp_pass) {
            if (pass.equals(string_)) {
                sum = sum + 1;
            }
        }
        */
        return sum;
    }
}
