package com.example.admin.she;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterationSetup2Activity extends AppCompatActivity {

    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_setup2);

        button = (Button) findViewById(R.id.button5);
        button1 = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin3();
            }
        });
    }

    public void openlogin(){
        Intent intent = new Intent(this, RegisterationSetup1Activity.class);
        startActivity(intent);
    }

    public void openlogin3(){
        Intent intent = new Intent(this , RegisterationSetup3Activity.class);
        startActivity(intent);
    }
}
