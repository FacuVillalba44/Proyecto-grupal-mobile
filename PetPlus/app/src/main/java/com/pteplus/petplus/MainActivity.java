package com.pteplus.petplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.pteplus.petplus.bd.BaseDeDatos;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_main);

        Button buttonTusMascotas = findViewById(R.id.btn_tus_mascotas);
        Button buttonTusVeterinarias = findViewById(R.id.btn_tus_veterinarias);
        Button buttonTusRecordatorios = findViewById(R.id.btn_tus_recordatorios);
        Button buttonAgregarMascota = findViewById(R.id.btn_agregar_mascota);
        Button buttonAgregarVeterinaria = findViewById(R.id.btn_agregar_veterinaria);

        int idUsuario = getIntent().getIntExtra(BaseDeDatos.COLUMN_ID_USUARIO, -1);


        buttonTusMascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TusMascotasActivity.class);
                intent.putExtra("id_usuario", idUsuario);
                startActivity(intent);
            }
        });

        buttonTusVeterinarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TusVeterinariasActivity.class);
                intent.putExtra("id_usuario", idUsuario);
                startActivity(intent);
            }
        });

        buttonTusRecordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TusRecordatoriosActivity.class);
                intent.putExtra("id_usuario", idUsuario);
                startActivity(intent);
            }
        });

        buttonAgregarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarMascotaActivity.class);
                intent.putExtra(BaseDeDatos.COLUMN_ID_USUARIO, idUsuario);
                startActivity(intent);
            }
        });

        buttonAgregarVeterinaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarVeterinariaActivity.class);
                intent.putExtra("id_usuario", idUsuario);
                startActivity(intent);
            }
        });


    }
}