package nit.fes.system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Display extends AppCompatActivity {
    private String name;
    private double pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        String id_name;
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

        WebView webview = findViewById(nit.fes.system.R.id.web_view);
        webview.loadUrl("https://b1.numazu-nit.com/");
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

    public void setName(String _name){this.name = _name;}
    public String getName(){return this.name;}
    public void setPro(double pro_){this.pro = pro_;}
    public double getPro(){return this.pro;}
}
