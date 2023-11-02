package com.proyectdevispc.petpulse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText et_email, et_pass;
    Button bt_iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_email=findViewById(R.id.et_email);
        et_pass=findViewById(R.id.et_pass);
        bt_iniciar=findViewById(R.id.bt_iniciar);

        bt_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarEmail(et_email);
                camposVacios(et_email,et_pass);
            }
        });
    }
    private boolean camposVacios(EditText et_email, EditText et_pass){
        String val_email = et_email.getText().toString();
        String val_pass = et_pass.getText().toString();
        if (!val_email.isEmpty() || !val_pass.isEmpty()) {
            return true;
        } else {
            Toast.makeText(this, "Este campo no puede estar vacio", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private boolean validarEmail(EditText et_email) {
        String val_email = et_email.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(val_email).matches()) {
            Toast.makeText(this, "Bienvenido a PetPulse", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Ingrese un e-mail valido", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}