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
import android.widget.ImageView;

import java.io.InputStream;

public class Main2Activity extends AppCompatActivity {
    //private Intent intent = new Intent(getApplication(), QrCodeReadInViewActivity.class);
    private String name = "error";
    private int count = 0;
    private int key;

    //private StringBuilder sb;
    //ここなんなんだろう
    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    //一応消してみる
    //追記2018.11.03 やっぱほしそう
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(nit.fes.system.R.layout.activity_main2);
        String id_name = "001";
        Intent i = getIntent();
        if(i.getStringExtra("NAME") != null) {
            id_name = i.getStringExtra("NAME");
            setName(id_name);
        }
        int key_intent;
        key_intent = i.getIntExtra("KEY",0);
        setKey(key_intent);

        /*
        if(i.getStringExtra("DATA") != null) {
           setName(i.getStringExtra("DATA"));
            TextView textView = findViewById(R.id.ReadData);
            textView.setText(getName());
            setCount(1);
        }*/

//ここに読み出しの部分を書きたい
//        try {
//            FileInputStream fileInputStream = openFileInput("data.txt");
//            data = fileInputStream.read();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        /*
        TextView write_text = findViewById(R.id.Write_Data);
        String data = "Read";
        write_text.setText(data);

*/
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
        findViewById(nit.fes.system.R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent1 = new Intent(getApplication(), QrCodeReadInViewActivity.class);
                Intent intent1 = new Intent(getApplication(), button_activity.class);
                intent1.putExtra("NAME",getName());
                intent1.putExtra("KEY",getKey());
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
                Intent intent3 = new Intent(getApplication(), Graph.class);
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
/*
    private void SetTextBox(String text){
        this.sb.append(text);
    }

    private StringBuilder getTextBox(){

        return this.sb;
    }

*/

}
