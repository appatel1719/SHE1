package com.example.admin.she;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;

    Context mContext;

    private TextView textView;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText)findViewById(R.id.login_editText_username);
        passwordEditText = (EditText)findViewById(R.id.login_editText_password);

        textView = (TextView) findViewById(R.id.textView50);
        loginButton = (Button) findViewById(R.id.button4);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(),RegisterationSetup1Activity.class);
                 startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });

        mContext = LoginActivity.this;

    }

    public void sendLoginRequest(){

        JSONObject postDictionary = new JSONObject();

        try{
            postDictionary.put("user_id",UserInformation.username);
            postDictionary.put("password",UserInformation.password);
        }catch (JSONException exception){
            exception.printStackTrace();
        }

        class SendDataToServer extends AsyncTask<String,String,String> {

            @Override
            protected String doInBackground(String... strings) {

                String JsonResponse = null;
                String JsonDATA = strings[0];
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                String boundary = "*****";
                Log.d("jsonData",JsonDATA);
                try {
                    URL url = new URL("http://192.168.8.194:8080/she/she3/login.php");
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
                    Log.i("json_response",JsonResponse);

                    try{
                        JSONObject jsonObject = new JSONObject(JsonResponse);

                        boolean success = jsonObject.getBoolean("loginSuccess");

                        if(success){

                            JSONObject detailsJSON = jsonObject.getJSONObject("details");


                            UserInformation.username = detailsJSON.getString("user_id");
                            UserInformation.name = detailsJSON.getString("user_name");
                            UserInformation.password = detailsJSON.getString("password");
                            UserInformation.email = detailsJSON.getString("user_email");
                            UserInformation.contactNumber = detailsJSON.getString("contact_num");
                            UserInformation.age = detailsJSON.getInt("user_age");
                            UserInformation.address = detailsJSON.getString("address");

                            Intent intent = new Intent(getApplicationContext(), tabView.class);
                            startActivity(intent);


                        }else{


                            //CAUTION: GENERATES ERROR WHEN CALLED. BECAUSE, NOT BEING CALLED FROM THE MAIN UI THREAD. THIS IS WORKER THREAD
                            Toast.makeText(mContext,"Cannot Login. Incorrect Username or Password!",Toast.LENGTH_SHORT).show();

                        }

                    }catch (JSONException exception){
                        Log.d("json_dataSend",exception.getLocalizedMessage());

                        Toast.makeText(mContext, "Cannot Sign-in at the moment.", Toast.LENGTH_SHORT).show();
                    }


                    return JsonResponse;


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


    public void performLogin(){

        UserInformation.username = usernameEditText.getText().toString();
        UserInformation.password = passwordEditText.getText().toString();

        sendLoginRequest();

        Intent intent = new Intent(getApplicationContext(), tabView.class);
        startActivity(intent);

    }


}
