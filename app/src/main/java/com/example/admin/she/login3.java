package com.example.admin.she;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login3 extends AppCompatActivity {

    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        button = (Button) findViewById(R.id.button8);
        button1 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin2();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin4();
            }
        });
    }

    public void openlogin2(){
        Intent intent = new Intent(this , login2.class);
        startActivity(intent);
    }

    public void openlogin4(){
        Intent intent = new Intent(this, login4.class);
        startActivity(intent);
    }
}
