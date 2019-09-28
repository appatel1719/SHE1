package com.example.admin.she;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private Button button;
    private Button button1;
    EditText name,address,age,id,number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.button7);
        button1 = (Button) findViewById(R.id.button6);
        name = (EditText) findViewById(R.id.editText7);
        address = (EditText) findViewById(R.id.editText8);
        age = (EditText) findViewById(R.id.editText9);
        id = (EditText) findViewById(R.id.editText5);
        number = (EditText) findViewById(R.id.editText6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin2();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin0();
            }
        });
    }

    public void openlogin2() {
        //validation of name, address, id , phone number
        if (isEmpty(name)) {
            name.setError("Enter your name!");
        }
        else if (isEmpty(address)) {
            address.setError("Enter valid address!");
        }
        else if (isEmpty(age)) {
            age.setError("Enter your age!");
        }
        else if (isEmail(id) == false) {
                id.setError("Enter valid email!");
        }
        else if(isValidPhone(number)){
                number.setError("Enter contact number");
        }
        else{
                    Intent intent = new Intent(this, login2.class);
                    startActivity(intent);
        }


    }

    public void openlogin0(){
        Intent intent = new Intent(this , login0.class);
        startActivity(intent);
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isValidPhone(EditText num) {
        CharSequence phone = num.getText().toString();
        return (!TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches());
    }
}
