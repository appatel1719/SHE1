package com.example.admin.she;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterationSetup3Activity extends AppCompatActivity {

    private Button confirmButton;

    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_registeration_setup3);

        confirmButton = findViewById(R.id.button_registerationConfirm);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameEditText = findViewById(R.id.editText_username);
                passwordEditText = findViewById(R.id.editText_registeration_password);

                UserInformation.username = usernameEditText.getText().toString();
                UserInformation.password = passwordEditText.getText().toString();

                sendRegisterationRequest();

            }
        });
    }

    public void sendRegisterationRequest(){

        JSONObject postDictionary = new JSONObject();

        try{
            postDictionary.put("submit",true);
            postDictionary.put("user_name",UserInformation.name);
            postDictionary.put("user_id",UserInformation.username);
            postDictionary.put("password",UserInformation.password);
            postDictionary.put("user_age",UserInformation.age);
            postDictionary.put("address",UserInformation.address);
            postDictionary.put("user_email",UserInformation.email);
            postDictionary.put("contact_num",UserInformation.contactNumber);
        }catch (JSONException exception){
            exception.printStackTrace();
        }

        class SendDataToServer extends AsyncTask <String,String,String> {

            @Override
            protected String doInBackground(String... strings) {

                String JsonResponse = null;
                String JsonDATA = strings[0];
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                String boundary = "*****";
                Log.d("jsonData",JsonDATA);
                try {
                    URL url = new URL("http://192.168.43.235:8080/SHE/she3/profile.php");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    // is output buffer writter
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    //urlConnection.setRequestProperty("Accept", "application/json");
                    //set headers and method
                    Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                    writer.write(JsonDATA);
                    // JSON data
                    writer.close();
                    InputStream inputStream = urlConnection.getInputStream();
                    //input stream
                    StringBuffer buffer = new StringBuffer();
                    if (inputStream == null) {
                        Log.d("json_issue","Input Stream is NULL");
                        Toast.makeText(getApplicationContext(),"Input Stream NULL",Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String inputLine;
                    while ((inputLine = reader.readLine()) != null)
                        buffer.append(inputLine + "\n");
                    if (buffer.length() == 0) {
                        Log.d("json_issue","Buffer Length is ZERO");
                        Toast.makeText(getApplicationContext(),"Buffer Length is ZERO",Toast.LENGTH_SHORT).show();

                        return null;
                    }
                    JsonResponse = buffer.toString();
                    //response data
                    Log.i("json_dataSend",JsonResponse);
                    return JsonResponse;
//                    try {
//                        //send to post execute
//                        return JsonResponse;
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    //return null;



                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (final IOException e) {
                            Log.e("json_dataSend", "Error closing stream", e);
                        }
                    }
                }
                return null;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }
        }

        if(postDictionary.length() > 0){
            //Toast.makeText(getApplicationContext(),"Sending JSON to server with length: " + postDictionary.length(),Toast.LENGTH_SHORT).show();

            try{
                Log.d("generatedJSON",postDictionary.toString(4));
            }catch (JSONException exception){
                Log.d("jsonException",exception.getLocalizedMessage());
            }

            new SendDataToServer().execute(String.valueOf(postDictionary));
        }



    }


}
