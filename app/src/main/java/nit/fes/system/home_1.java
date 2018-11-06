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
    public int count = 1;
    private int key;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_1);
        String id_name = "001";
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
        ImageView image = findViewById(nit.fes.system.R.id.view);


        AssetManager assets = getResources().getAssets();

        // try-with-resources
        try (InputStream istream = assets.open(file)){
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            image.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String spinnerItems[] = {"1","2","3","4","5"};

        Spinner spinner = findViewById(R.id.spinner);

        // ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinner に adapter をセット
        spinner.setAdapter(adapter);

        // リスナーを登録
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            public int _count;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
                //たぶんこの書き方は動作しない
                //countがうまく更新される方法を考えたい

            }
            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //悲しいね
            }
        });
        String item = (String)spinner.getSelectedItem();
        setCount(Integer.parseInt(item));

        findViewById(nit.fes.system.R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent1 = new Intent(getApplication(), QrCodeReadInViewActivity.class);
                Intent intent1 = new Intent(getApplication(), button_activity.class);
                intent1.putExtra("NAME",getName());
                intent1.putExtra("KEY",getKey());
                intent1.putExtra("count",getCount());
                startActivity(intent1);
            }
        });
        findViewById(nit.fes.system.R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplication(), Kuchikomi.class);
                intent2.putExtra("NAME",getName());
                intent2.putExtra("KEY",getKey());
                startActivity(intent2);

            }
        });
        findViewById(nit.fes.system.R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplication(), Display.class);
                intent3.putExtra("NAME",getName());
                intent3.putExtra("KEY",getKey());
                int num = getCount();
                intent3.putExtra("Count",1);
                startActivity(intent3);
            }
        });
        findViewById(nit.fes.system.R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplication(), MainActivity.class);
                //intent4.putExtra("NAME",getName());
                startActivity(intent4);
            }
        });


    }

    private void setName(String intent_text){
        this.name = intent_text;
        return;
    }

    private String getName(){

        return name;
    }
    private void setCount(int a){
        this.count +=a;
    }

    private int getCount(){
        return this.count;
    }
    public void setKey(int intent_key){
        this.key = intent_key;
    }
    public int  getKey(){
        return this.key;
    }
}
