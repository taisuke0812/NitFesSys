package nit.fes.system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Kuchikomi extends AppCompatActivity {
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(nit.fes.system.R.layout.activity_kuchikomi);

        String id_name;
        Intent i = getIntent();
        if(i.getStringExtra("NAME") != null) {
            id_name = i.getStringExtra("NAME");
            setName(id_name);
        }
        WebView webview = findViewById(nit.fes.system.R.id.web);
        webview.loadUrl("https://twitter.com/search?f=tweets&vertical=default&q=%23NITFes&src=typd");
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setVerticalScrollBarEnabled(true);

        findViewById(nit.fes.system.R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Main2Activity.class);
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
}
