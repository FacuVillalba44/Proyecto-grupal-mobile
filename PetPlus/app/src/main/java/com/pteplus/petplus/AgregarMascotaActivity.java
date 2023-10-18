package com.pteplus.petplus;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pteplus.petplus.bd.DbMascota;

public class AgregarMascotaActivity extends AppCompatActivity {
    private Button btnAgregar, btnCancelar;

    private EditText etNombreMascota, etFechaNacimiento, etRaza, etEspecie, etSexo;

    private Spinner spinner1;

    private String[] opciones = {"Macho", "Hembra"};

    DbMascota dbMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnCancelar = findViewById(R.id.btnCancelar);
        etNombreMascota = findViewById(R.id.etNombreMascota);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etRaza = findViewById(R.id.etRaza);
        etEspecie = findViewById(R.id.etEspecie);
        spinner1 = findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,opciones);

        spinner1.setAdapter(adapter);

        dbMascota = new DbMascota(this);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombreMascota.getText().toString();
                String fecha_nacimiento = etFechaNacimiento.getText().toString();
                String especie = etEspecie.getText().toString();
                String raza = etRaza.getText().toString();
                String sexo = spinner1.getSelectedItem().toString();

                long agregarMascota = dbMascota.agregarMascota(nombre, fecha_nacimiento, especie, raza, sexo);

                /*if(agregarMascota>0){
                    Toast.makeText(AgregarMascotaActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AgregarMascotaActivity.this, TusMascotasActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AgregarMascotaActivity.this, "Registro Fallido", )
                }*/


            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarMascotaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}