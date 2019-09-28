package com.example.admin.she;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login3 extends AppCompatActivity {

    private Button button;
    private Button button1;
    EditText name , pass , confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        button = (Button) findViewById(R.id.button8);
        button1 = (Button) findViewById(R.id.button2);
        name = (EditText) findViewById(R.id.editText2);
        pass = (EditText) findViewById(R.id.editText4);
        confirmPass = (EditText) findViewById(R.id.editText10);

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
        if (isEmpty(name)) {
            name.setError("Enter your name!");
        }
        else if (isEmpty(pass)) {
            name.setError("Enter valid password!");
        }
        else if (isEmpty(confirmPass)) {
            name.setError("Enter your password again!");
        }
        else{
            Intent intent = new Intent(this, login4.class);
            startActivity(intent);
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}
