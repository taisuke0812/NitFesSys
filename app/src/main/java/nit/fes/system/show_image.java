package nit.fes.system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class show_image extends AppCompatActivity {
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(nit.fes.system.R.layout.activity_show_image);
        String id_name;
        Intent i = getIntent();
        if(i.getStringExtra("NAME") != null) {
            id_name = i.getStringExtra("NAME");
            setName(id_name);
        }
        ImageView imageView = (ImageView) findViewById(nit.fes.system.R.id.gifView);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(nit.fes.system.R.raw.slot).into(target);

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