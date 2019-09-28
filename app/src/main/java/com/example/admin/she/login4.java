package com.example.admin.she;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class login4 extends AppCompatActivity {

    private TextView textView;
    private Button button;
    EditText name, pass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login4);

        textView = (TextView) findViewById(R.id.textView50);
        button = (Button) findViewById(R.id.button4);
        name = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText0);

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
        Intent intent = new Intent(this , Login.class);
        startActivity(intent);
    }

    public void openTab(){
        if (isEmpty(name) || isEmail(name) == false) {
            name.setError("Enter your name or Email ID!");
        }
        else if (isEmpty(pass)) {
            name.setError("Enter valid password!");
        }
        else{
            Intent intent = new Intent(this, activity_contact.class);
            startActivity(intent);
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
