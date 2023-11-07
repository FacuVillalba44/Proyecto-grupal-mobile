package com.proyectdevispc.petpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_email, et_pass;
    Button bt_iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_email=findViewById(R.id.ediTextemail);
        et_pass=findViewById(R.id.ediTextPassword);
        bt_iniciar=findViewById(R.id.buttonInisioSesion);

        bt_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validacion = validarCampos(et_email,et_pass);
                //si la validación es correcta, inicia.
                 if (validacion){
                 Iniciar();
                }
            }
        });
    }
    public void form_registrar (View view){
        Intent form_registro = new Intent(this, Form_registrarse.class);
        startActivity(form_registro);
    }
    public void Iniciar(){
        Intent inicio = new Intent(this,   Inicio.class);
        startActivity(inicio);
    }
    private boolean validarCampos(EditText et_email, EditText et_pass) {
        String email = et_email.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            // Mostrar mensaje de error
            Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Mostrar mensaje de error
            Toast.makeText(getApplicationContext(), "Ingrese un e-mail válido", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            // Los campos están completos y el email es válido inicia sesion
            Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}