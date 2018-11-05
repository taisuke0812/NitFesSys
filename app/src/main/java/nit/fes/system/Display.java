package nit.fes.system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_display);

        // リニアレイアウトの設定
        LinearLayout layout = new LinearLayout(this);
        // orientationは垂直方向
        layout.setOrientation(LinearLayout.VERTICAL);

        // Layoutの横・縦幅の指定
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        setContentView(layout);

        RadioButton radio1 = new RadioButton(this);
        RadioButton radio2 = new RadioButton(this);
        RadioButton radio3 = new RadioButton(this);
        RadioButton radio4 = new RadioButton(this);

        radio1.setText("taisuke");
        radio2.setText("kanazawa");
        radio3.setText("murakami");

        RadioGroup radio_group = new RadioGroup(this);
        radio_group.addView(radio1);
        radio_group.addView(radio2);
        radio_group.addView(radio3);
        radio_group.addView(radio4);
        layout.addView(radio_group);
    }
}
