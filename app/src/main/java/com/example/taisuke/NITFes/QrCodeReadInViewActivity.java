package com.example.taisuke.ankotest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import android.content.Intent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalTime;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;


public class QrCodeReadInViewActivity extends AppCompatActivity {

    private CompoundBarcodeView mBarcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_read_in_view);

        mBarcodeView = (CompoundBarcodeView) findViewById(R.id.barcodeView);
        mBarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult barcodeResult) {
               // String time = "";

                //TextView textView = (TextView)findViewById(R.id.textView);
                //textView.setText(barcodeResult.getText());
                String read_string = barcodeResult.getText();
                if (read_string.equals("お好み焼き")) {
                    try {
                        FileOutputStream fileOutputstream = openFileOutput("register_data", Context.MODE_PRIVATE);
                        int num = 10;
                        fileOutputstream.write(num);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                    //時刻だけじゃなくて日付も送れないとまずいよね
                   // LocalTime time_data = LocalTime.now();
                    //time = toStr(time_data);
                    Date date = new Date();
                    String date_data = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
                    data send_data = new data(1,"[" + date_data + "]");
                    sendData(send_data);

                    Intent intent = new Intent(getApplication(), Main2Activity.class);
                    //intent.putExtra("DATA", barcodeResult.getText());
                    intent.putExtra("DATA",date_data);
                    startActivity(intent);
                }

            }

            @Override
            public void possibleResultPoints(List<ResultPoint> list) {
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.O)
    public String toStr(LocalTime time_data) {
        //String format;
        String time;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //DateTimeよくわからん


        time = dateTimeFormatter.format(time_data);
        return time;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBarcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBarcodeView.pause();
    }

    public void sendData(data _data) {
        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference("data");
        String key = dataref.push().getKey();
        Map<String, Object> map = new HashMap<>();
        map.put(key, _data.toMap());
        dataref.updateChildren(map);
    }
}



    //ここから
    //findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
      //  @Override
        //public void onClick(View v) {
          //  val intent = Intent(QrCodeReadInViewActivity.this,NextActivity);
            //startActivity(intent);
        //}
    //});//ここまでエラーの原因
