package com.example.mychatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mychatapp.ui.Tools;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    Button girisYAp;
    EditText email,sifre;
    FirebasaAuth firebasaAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        girisYAp = (Button) findViewById(R.id.buttongirisyap);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        sifre = (EditText) findViewById(R.id.editTextTextPassword);

        firebasaAuth = FirebaseAuth.getInstance();

        girisYAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girisYApClick();
            }
        });

    }
    private  void girisYApClick() {
        String userEmail = email.getText().toString();
        String userSifre = sifre.getText().toString();
        if(userEmail.isEmpty()){
            Tools.showMassage("E-Mail boş bırakılamaz!");

        }
        if(userSifre.isEmpty()|| userEmail.length()<6){
            Tools.showMassage("Şifre gecersizsir!");

        }
    }
    public void girisyapClick(View view) {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}