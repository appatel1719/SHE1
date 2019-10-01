//https://www.simplifiedcoding.net/json-parsing-in-android/
package com.example.admin.she;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import org.json.JSONArray;
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

import ir.sohreco.circularpulsingbutton.CircularPulsingButton;

public class EmergencyTab extends Fragment{

    String[] name  = new String[5];
    String[] number = new String[5];
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    CircularPulsingButton emergencyButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_emergency, container, false);
        emergencyButton = (CircularPulsingButton)view.findViewById(R.id.btn_alert_emergency);

        //sendUserIDToServer();
        getJSONofContactInfo("http://192.168.43.235:8080/SHE/she3/fetchcontact.php?user_id="+UserInformation.username);

        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                for (int i = 0; i < name.length; i++) {

                    String Relation, ph_number;
                    Relation = name[i];
                    ph_number = number[i];
                    if (checkPermission(Manifest.permission.SEND_SMS) && ph_number!=null) {
                        PendingIntent pi = PendingIntent.getActivity(getContext(), 0,
                                new Intent(getContext(), EmergencyTab.class), 0);
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(ph_number.trim(), null, "Hello Testing is last time.....", pi, null);
                        Toast.makeText(getContext(), "Message Sent", Toast.LENGTH_SHORT).show();

                    } else if(ph_number!=null) {

                        Toast.makeText(getContext(), "Cannot Send SMS. Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        return view;

    }

    private void getJSONofContactInfo(final String urlWebService) {
        class GetJSON extends AsyncTask <Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
               // Toast.makeText(getContext(),s, Toast.LENGTH_SHORT).show();
                Log.d("String " , s);
                try {
                    getJsonData(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            private void getJsonData(String s) throws JSONException {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {

                    //getting json object from the json array
                    JSONObject obj = jsonArray.getJSONObject(i);

                    //getting the name from the json object and putting it inside string array
                    name[i] = obj.getString("relation");
                    number[i] = obj.getString("contact_no");
                   Log.d("----namber----: ",number[i]);
                    Log.d("----name----: ",name[i]);
                }




            }

        @Override
            protected String doInBackground(Void... voids) {



                try {
                    URL url = new URL(urlWebService);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    StringBuilder sb = new StringBuilder();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;

                    while ((json = bufferedReader.readLine()) != null) {
                        //Log.d("---------------------- " , json);
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }





    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(getContext(),permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}



