package nit.fes.system;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class home_1 extends AppCompatActivity {
    private String name = "error";
    private int count = 1;
    private int key;
    private int kind;
    private double pro;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_1);
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


        String img = "img_";
        String file = img + id_name + ".jpg";
        ImageView image = findViewById(nit.fes.system.R.id.image);


        AssetManager assets = getResources().getAssets();

        // try-with-resources
        try (InputStream istream = assets.open(file)){
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            image.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String spinnerItems[] = {"1","2","3","4","5","6","7","8","9","10","15","20","25","30","35","40","45","50"};
        String spinnerItems_[] = {"0","1","2","3","4","5","6","7","8"};
        final Spinner spinner = findViewById(R.id.spinner);
        final Spinner spinner_ = findViewById(R.id.spinner_);

        // ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        ArrayAdapter<String> adapter_ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems_);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // spinner に adapter をセット
        spinner.setAdapter(adapter);
        spinner_.setAdapter(adapter_);

        findViewById(nit.fes.system.R.id.buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent1 = new Intent(getApplication(), QrCodeReadInViewActivity.class);
                String item = spinner.getSelectedItem().toString();
                int item_ = Integer.parseInt(item);
                String kind_ = spinner_.getSelectedItem().toString();
                int _kind_ = Integer.parseInt(kind_);
                Intent intent1 = new Intent(getApplication(), button_activity.class);
                intent1.putExtra("NAME",getName());
                intent1.putExtra("KEY",getKey());
                intent1.putExtra("count",item_);
                intent1.putExtra("kind",_kind_);
                intent1.putExtra("pro",getPro());
                //intent1.putExtra("count",3);
                startActivity(intent1);
            }
        });
        findViewById(nit.fes.system.R.id.review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplication(), Kuchikomi.class);
                intent2.putExtra("NAME",getName());
                intent2.putExtra("KEY",getKey());
                intent2.putExtra("pro",getPro());
                startActivity(intent2);

            }
        });
        findViewById(nit.fes.system.R.id.graph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //グラフ表示
                Intent intent3 = new Intent(getApplication(), Display.class);
                intent3.putExtra("NAME",getName());
                intent3.putExtra("KEY",getKey());
                startActivity(intent3);
            }
        });
        findViewById(nit.fes.system.R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //グラフ表示
                Intent intent5 = new Intent(getApplication(), change_probability.class);
                intent5.putExtra("NAME",getName());
                intent5.putExtra("KEY",getKey());
                startActivity(intent5);
            }
        });
        findViewById(nit.fes.system.R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ログアウト処理を施す
                Intent intent4 = new Intent(getApplication(), MainActivity.class);
                //intent4.putExtra("NAME",getName());
                startActivity(intent4);
            }
        });
        findViewById(nit.fes.system.R.id.history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //購入履歴ページを見れるようにする
                Intent intent6 = new Intent(getApplication(), history.class);
                intent6.putExtra("NAME",getName());
                intent6.putExtra("KEY",getKey());
                startActivity(intent6);
            }
        });


    }

    public void setName(String intent_text){
        name = intent_text;
    }
    public String getName(){
        return name;
    }
    public void setCount(int a){
        this.count = a;
    }
    public int getCount(){
        return this.count;
    }
    public void setKey(int intent_key){
        key = intent_key;
    }
    public int  getKey(){
        return key;
    }

    public int getKind(){
        return this.kind;
    }
    public void setKind(int kind_){
        this.kind = kind_;
    }
    public void setPro(double pro_){this.pro = pro_;}
    public double getPro(){return this.pro;}
}
