package com.example.admin.she;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login4 extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = (TextView) findViewById(R.id.textView50);
        button = (Button) findViewById(R.id.button4);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTab();
            }
        });

    }

    public void openlogin(){
        Intent intent = new Intent(this , RegisterationSetup1Activity.class);
        startActivity(intent);
    }

    public void openTab(){
        Intent intent = new Intent(this, tabView.class);
        startActivity(intent);
    }
}
