package com.proyectdevispc.petpulse;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Form_registrarse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_registrarse);
    }
    public void btn_cancelar(View view){
        Intent form_login= new Intent(this, MainActivity.class);
        startActivity(form_login);
    }
}