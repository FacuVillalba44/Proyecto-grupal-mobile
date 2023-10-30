package com.pteplus.petplus;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.pteplus.petplus.bd.DbMascota;
import com.pteplus.petplus.entidades.Mascotas;

import java.util.Calendar;

public class EditarMascotasActivity extends AppCompatActivity {
    private EditText etNombreMascota, etEspecie, etRaza, etSexo;
    private EditText etFechaNacimiento;
    int id = 0;
    Mascotas mascota;
    private Button btnEditar;

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

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", 0);
            String nombre = intent.getStringExtra("nombre");
            String fechaNacimiento = intent.getStringExtra("fechaNacimiento");
            String especie = intent.getStringExtra("especie");
            String raza = intent.getStringExtra("raza");
            String sexo = intent.getStringExtra("sexo");

            // Llena los EditText con los datos recibidos
            etNombreMascota.setText(nombre);
            etFechaNacimiento.setText(fechaNacimiento);
            etEspecie.setText(especie);
            etRaza.setText(raza);
            etSexo.setText(sexo);

        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditarMascotasActivity.this, EditarMascotasActivity.class);
                intent.putExtra("id_mascota", id);
                startActivity(intent);
            }
        });
    }
}