package nit.fes.system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.Random;

public class show_image extends AppCompatActivity {
    private String name;
    private int key;
    private double pro = 0.2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(nit.fes.system.R.layout.activity_show_image);
        String id_name;
        int key_intent;
        String id_pro;
        Intent i = getIntent();
        if(i.getStringExtra("NAME") != null) {
            id_name = i.getStringExtra("NAME");
            setName(id_name);
        }
        if(i.getStringExtra("pro") != null) {
            id_pro = i.getStringExtra("pro");
            setPro(Double.parseDouble(id_pro));
        }

        key_intent = i.getIntExtra("KEY",0);
        setKey(key_intent);
        ImageView imageView = (ImageView) findViewById(nit.fes.system.R.id.gifView);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView);
        int sum = 0;
        int test;
        Random rand = new Random();
        //乱数が一様に分布するように最初に回しておく
        for(int counter_ = 0; counter_ < 20;counter_++){
            test = rand.nextInt();
        }
        for(int counter = 0 ; counter < 10 ; counter++) {
            sum += rand.nextInt(11);
        }
        if(sum >= 100*getPro()){
            Glide.with(this).load(nit.fes.system.R.raw.slot_).into(target);
        }else{
            Glide.with(this).load(nit.fes.system.R.raw.slot).into(target);
        }


        findViewById(nit.fes.system.R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), home_1.class);
                intent.putExtra("NAME",getName());
                intent.putExtra("KEY",getKey());
                intent.putExtra("pro",getPro());
                startActivity(intent);
            }
        });

    }


    public void setName(String intent_name){
        this.name = intent_name;
    }
    public String getName(){
        return this.name;
    }

    public void setKey(int intent_key){
        this.key = intent_key;
    }
    public int  getKey(){
        return this.key;
    }
    public void setPro(double pro_){this.pro = pro_;}
    public double getPro(){return this.pro;}
}
