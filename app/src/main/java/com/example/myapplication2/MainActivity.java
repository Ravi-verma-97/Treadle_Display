package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button click;
    EditText chno,apikey;
    String s1,s2;
    public static String s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Boolean isFirstTime;
        final SharedPreferences app_preferences = getSharedPreferences("file",MODE_PRIVATE);
        final SharedPreferences.Editor editor = app_preferences.edit();
        isFirstTime = app_preferences.getBoolean("isFirstTime", true);
        if(isFirstTime) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            click=(Button)findViewById(R.id.fetch);
            chno=(EditText)findViewById(R.id.cno);
            apikey=(EditText)findViewById(R.id.key);
            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s1 = chno.getText().toString();
                    s2 = apikey.getText().toString();
                    s3 = "https://api.thingspeak.com/channels/" + s1 + "/feeds.json?api_key=" + s2 + "&results=2";
                    Intent intent = new Intent(MainActivity.this, sec.class);
                    editor.putString("s1",s1);
                    editor.putString("s2",s2);
                    Toast.makeText(getApplicationContext(),app_preferences.getString("s1",s1),Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            });
            editor.putBoolean("isFirstTime", false);
            editor.putString("s1",s1);
            editor.putString("s2",s2);
            editor.commit();
        }
        else {
            s1=app_preferences.getString("s1",null);
            s2=app_preferences.getString("s2",null);
            s3 = "https://api.thingspeak.com/channels/" + s1 + "/feeds.json?api_key=" + s2 + "&results=2";
            Toast.makeText(getApplicationContext(),s3,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, sec.class);
            startActivity(intent);
        }
    }
}
