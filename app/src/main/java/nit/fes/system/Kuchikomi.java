package nit.fes.system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Kuchikomi extends AppCompatActivity {
    private String name;
    private int key;
    private double pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(nit.fes.system.R.layout.activity_kuchikomi);
        int key_intent;
        String id_name;
        String id_pro;
        Intent i = getIntent();
        if(i.getStringExtra("NAME") != null) {
            id_name = i.getStringExtra("NAME");
            setName(id_name);
        }

        key_intent = i.getIntExtra("KEY",0);
        setKey(key_intent);
        WebView webview = findViewById(nit.fes.system.R.id.web);
        webview.loadUrl("https://twitter.com/search?f=tweets&vertical=default&q=%23NIT_B1&src=typd");
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setVerticalScrollBarEnabled(true);

        findViewById(nit.fes.system.R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), home_1.class);
                intent.putExtra("NAME",getName());
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
