package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class sec extends AppCompatActivity {
    public static TextView cd;
    TextView pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        pd=(TextView)findViewById(R.id.pd);
        cd=(TextView)findViewById(R.id.cd);
        Thread t=new Thread(){
            @Override
            public void run()
            {
                while(!isInterrupted()){
                    try {
                        Thread.sleep(5000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.setText(cd.getText().toString());
                                fetchData process=new fetchData();
                                process.execute();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        t.start();
    }
}
