package com.pteplus.petplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.pteplus.petplus.bd.DbMascota;
import com.pteplus.petplus.entidades.Mascotas;

public class VerMascotaActivity extends AppCompatActivity {
    private EditText etNombreMascota, etEspecie, etRaza, etSexo;
    private EditText etFechaNacimiento;

    int id_mascota = 0;
    Mascotas mascota;

    private Button btnEditar, btnEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_mascota);

        etNombreMascota = findViewById(R.id.etNombreMascota);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etEspecie = findViewById(R.id.etEspecie);
        etRaza = findViewById(R.id.etRaza);
        etSexo = findViewById(R.id.etSexo);

        btnEditar = findViewById(R.id.btnEditarMascota);
        btnEliminar = findViewById(R.id.btnEliminarMascota);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();

            if(extras==null){
                id_mascota = Integer.parseInt(null);
            }
            else{
                id_mascota = extras.getInt("id_mascota");
            }
        }
        else{
            id_mascota = (int) savedInstanceState.getSerializable("id_mascota");

        }
        DbMascota dbMascota = new DbMascota(VerMascotaActivity.this);
        mascota = dbMascota.verMascota(id_mascota);

        if(mascota != null){
            etNombreMascota.setText(mascota.getNombre());
            etFechaNacimiento.setText(mascota.getFecha_nacimiento());
            etEspecie.setText(mascota.getEspecie());
            etRaza.setText(mascota.getRaza());
            etSexo.setText(mascota.getSexo());
        }
    }
}