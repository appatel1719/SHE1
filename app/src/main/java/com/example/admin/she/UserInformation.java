package com.example.admin.she;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

public class UserInformation {

    static String name;
    static String username;
    static String address;
    static int age;
    static String email;
    static String contactNumber;

    static String password;

    public UserInformation(String name, String username, String address, int age, String email, String contactNumber) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.age = age;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public UserInformation(String name, String address, int age, String email, String contactNumber) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public void aveToPreferences(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_details",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("user_name",UserInformation.name);
        editor.putString("password",UserInformation.password);
        editor.putString("user_id",UserInformation.username);
        editor.putInt("user_age",UserInformation.age);
        editor.putString("address",UserInformation.address);
        editor.putString("user_email",UserInformation.email);
        editor.putString("contact_num",UserInformation.contactNumber);

        editor.commit();


    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
