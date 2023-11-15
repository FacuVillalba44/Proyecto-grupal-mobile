package com.fj.developferjuarez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_mascota extends AppCompatActivity {
 private MascotaDataSource dataSource;
 private EditText editTextNombre, getEditTextRaza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);
        Button btnVolverveter = findViewById(R.id:button6);
        dataSource = new MascotaDataSource(this);
        dataSource.open();
        editTextNombre = findViewById(R.id.editTextNombre);
        getEditTextRaza = findViewById(R.id.editTextRaza);
        button btnGuardar = findViewById(R.id.btnGuardarMascota);

        btnGuardar.setOnClickListener(new view.OnClickListener()){
            public void onclick(View v) {
                guardarMascota();
            }
        });
        obtenerYMostrarMascotas();
    }

    private void guardarMascota() {
        String nombre   = editTextNombre.getText().toString();
        String raza = editTextRaza.getText().toString();

        if(!nombre.isEmpty() && !raza.isEmpty()) {
            dataSource.insertMascota(nombre, raza);

            editTextNombre.getText().clear();
            getEditTextRaza.getText().clear();

            obtenerYMostrarMascotas();
        } else {
            Object toast;
            toast.makeText(this, "Por favor, ingrese nombre y raza", toast.length_short).show();

        }
    }
        private void obtenerYMostrarMascotas() {
            Cursor cursor = dataSource.getAllMascotas();
            if (cursor.moveToFirst()) {
                do{

                    String nombre = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE));
                    String raza = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RAZA));

                    toast.makeText(this, "Nombre: "+ nombre + ", Raza: " + raza, Toast.LENGTH_SHORT).show();

                }while (cursor.moveToNext());

                }
            }

            protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
            }
        }

        btnVolverveter.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(activity_mascota.this, activity_mascota.class );
            startActivity(intent);

        });

    }
}