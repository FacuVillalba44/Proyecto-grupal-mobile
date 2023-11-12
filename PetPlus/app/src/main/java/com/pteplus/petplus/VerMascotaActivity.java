package com.pteplus.petplus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pteplus.petplus.bd.DbMascota;
import com.pteplus.petplus.entidades.Mascotas;

public class VerMascotaActivity extends AppCompatActivity {
    private EditText etNombreMascota, etEspecie, etRaza, etSexo;
    private EditText etFechaNacimiento;
    int id_mascota = 0;
    int id_usuario = 0;
    Mascotas mascota;
    private Button btnEditar, btnEliminar;
    boolean exito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_mascota);

        etNombreMascota = findViewById(R.id.etNombreMascota);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etFechaNacimiento.setFocusable(false);
        etEspecie = findViewById(R.id.etEspecie);
        etRaza = findViewById(R.id.etRaza);
        etSexo = findViewById(R.id.etSexo);

        btnEditar = findViewById(R.id.btnEditarMascota);
        btnEliminar = findViewById(R.id.btnEliminarMascota);

        DbMascota dbMascota = new DbMascota(VerMascotaActivity.this);

        Intent intent = getIntent();
        if (intent != null) {
            id_mascota = intent.getIntExtra("id_mascota", 0);
            String nombre = intent.getStringExtra("nombre");
            String fechaNacimiento = intent.getStringExtra("fechaNacimiento");
            String especie = intent.getStringExtra("especie");
            String raza = intent.getStringExtra("raza");
            String sexo = intent.getStringExtra("sexo");
            id_usuario = intent.getIntExtra("id_usuario", -1);


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
                String nombre = etNombreMascota.getText().toString();
                String fechaNac = etFechaNacimiento.getText().toString();
                String especie = etEspecie.getText().toString();
                String raza = etRaza.getText().toString();
                String sexo = etSexo.getText().toString();


                if (!nombre.equals("") && !fechaNac.equals("") && !especie.equals("") && !raza.equals("") && !sexo.equals("")) {
                    DbMascota dbMascota = new DbMascota(VerMascotaActivity.this);

                    exito = dbMascota.editarMascota(id_mascota, nombre, fechaNac, especie, raza, sexo);

                    if (exito) {
                        Toast.makeText(VerMascotaActivity.this, "Registro modificado Correctamente", Toast.LENGTH_SHORT).show();

                        // Actualiza la lista de mascotas
                        tusMascotas();
                    } else {
                        Toast.makeText(VerMascotaActivity.this, "Fallo al modificar Registro", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(VerMascotaActivity.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerMascotaActivity.this);
                builder.setMessage("Desea eliminar esta MAscota??").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(dbMascota.eliminarMascota(id_mascota)){
                            tusMascotas();
                        }

                    }
                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        }).show();
            }
        });
    }
    private void tusMascotas(){
        Intent intent = new Intent(VerMascotaActivity.this, TusMascotasActivity.class);
        intent.putExtra("id_usuario", id_usuario);
        startActivity(intent);
        finish();
    }

}