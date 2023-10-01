package com.pteplus.petplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextEmail, editTextPassword1, editTextPassword2 ;
    private Button btnRegistro;
    private ImageView imageView;

    private RadioGroup radioGroup;

    private RadioButton radioMacho, radioHembra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword1 = findViewById(R.id.editTextPassword1);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        btnRegistro = findViewById(R.id.btnRegistro);
        imageView = findViewById(R.id.imageview);
        radioGroup = findViewById(R.id.radioGroup);
        radioMacho = findViewById(R.id.radioMacho);
        radioHembra = findViewById(R.id.radioHembra);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombre.getText().toString();
                String email = editTextEmail.getText().toString();
                String password1 = editTextPassword1.getText().toString();
                String password2 = editTextPassword2.getText().toString();

                int seleccion = radioGroup.getCheckedRadioButtonId();

                if (seleccion == radioMacho.getId()){
                    //guardamos en la bbdd que es macho
                    Toast.makeText(getApplicationContext(), "seleccionaste Sexo Macho", Toast.LENGTH_SHORT).show();
                }
                else if (seleccion == radioHembra.getId()){
                    //guardamos sexo hembra
                }
                else {
                    //no se debe poder guardar el registro ya que no completo todos los datos necesarios
                }





                Toast.makeText(RegistroActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}