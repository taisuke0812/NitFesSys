package nit.fes.system;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class reg {
    private String id;
    private String pass;
    private String pro;

    public reg(String id_,String pass_,String pro_){
        this.id = id_;
        this.pass = pass_;
        this.pro = pro_;
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
    public void setPro(String pro_){this.pro = pro_;}
    public String getPro(){return this.pro;}

    @Exclude
    public Map<String,String> toMap(){
        HashMap<String,String> hashmap = new HashMap();
        hashmap.put("id",id);
        hashmap.put("pass",pass);
        hashmap.put("pro",pro);
        return hashmap;
    }
}


