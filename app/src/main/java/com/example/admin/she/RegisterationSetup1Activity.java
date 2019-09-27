package com.example.admin.she;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterationSetup1Activity extends AppCompatActivity {

    private Button nextButton;

    EditText nameEditText;
    EditText addressEditText;
    EditText ageEditText;
    EditText emailEditText;
    EditText contactNoEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_setup1);


        nameEditText = (EditText)findViewById(R.id.editText_name);
        addressEditText = (EditText)findViewById(R.id.editText_address);
        ageEditText = (EditText)findViewById(R.id.editText_age);
        emailEditText = (EditText)findViewById(R.id.editText_email);
        contactNoEditText = (EditText)findViewById(R.id.edit_contactnumber);


        nextButton = (Button) findViewById(R.id.btn_next_1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserInformation.name = nameEditText.getText().toString();
                UserInformation.address = addressEditText.getText().toString();
                UserInformation.age = Integer.parseInt(ageEditText.getText().toString());
                UserInformation.contactNumber = contactNoEditText.getText().toString();
                UserInformation.email = emailEditText.getText().toString();

                Intent intent = new Intent(getApplicationContext() , RegisterationSetup2Activity.class);
                startActivity(intent);
            }
        });

    }




}
