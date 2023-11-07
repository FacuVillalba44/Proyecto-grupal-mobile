package com.proyectdevispc.petpulse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Form_registrarse extends AppCompatActivity {
    EditText et_email,et_nombre,et_apellido,et_pass1,et_pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_registrarse);

        et_email = findViewById(R.id.ediTextEmail);
        et_nombre = findViewById(R.id.ediTextNombre);
        et_apellido = findViewById(R.id.ediTextApellido);
        et_pass1 = findViewById(R.id.ediTextPassword1);
        et_pass2 = findViewById(R.id.ediTextPassword2);


    }
    private  boolean validarCampos(EditText et_email, EditText et_nombre, EditText et_apellido, EditText et_pass1,EditText et_pass2){
        String email = et_email.getText().toString().trim();
        String nombre = et_nombre.getText().toString().trim();
        String apellido = et_apellido.getText().toString().trim();
        String pass1 = et_pass1.getText().toString().trim();
        String pass2 = et_pass2.getText().toString().trim();
        if (email.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            // Mostrar mensaje de error
            Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Mostrar mensaje de correo no valido
            Toast.makeText(getApplicationContext(), "Ingrese un e-mail válido", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!pass1.equals(pass2)){
            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(getApplicationContext(), "Datos correctos", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    public void btn_registrar(View view){
        validarCampos(et_email,et_nombre,et_apellido,et_pass1,et_pass2);
    }
    public void btn_cancelar(View view){
        Intent form_login= new Intent(this, MainActivity.class);
        startActivity(form_login);
    }

}