
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

    private String id;
    private String pass;
    public int Case;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(nit.fes.system.R.layout.activity_authorized);

        Intent i = getIntent();

        if (i.getStringExtra("id") != null) {
            setId(i.getStringExtra("id"));
        }
        if (i.getStringExtra("pass") != null) {
            setPass(i.getStringExtra("pass"));
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("register/" + getId());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Intent back = new Intent(getApplication(), MainActivity.class);
                Map<String, Object> something = (Map<String, Object>) dataSnapshot.getValue();
                String correct_id = something.get("id").toString();
                String correct_pass = something.get("pass").toString();
                String num_kind = something.get("num").toString();

                if (correct_id.equals(getId())) {
                    if (correct_pass.equals(getPass())){
                        int num = Integer.parseInt(num_kind);
                        if(num == 1){
                            Intent intent_1 = new Intent(getApplication(),home_1.class);
                            intent_1.putExtra("NAME",getId());
                            startActivity(intent_1);
                        }else if(num == 2){

                        }else if(num == 3){

                        }else if(num == 4){

                        }
                        Intent go = new Intent(getApplication(), Main2Activity.class);
                        go.putExtra("NAME",getId());
                        startActivity(go);
                    } else
                        startActivity(back);
                    }
                }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
            }

        });



    }


    public void setId(String id_){this.id = id_;}
    public void setPass(String pass_){this.pass = pass_;}

    public String getId(){return id;}
    public String getPass(){return pass;}




}

