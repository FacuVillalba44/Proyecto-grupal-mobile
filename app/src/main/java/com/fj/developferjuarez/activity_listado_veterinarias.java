package com.fj.developferjuarez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class activity_listado_veterinarias extends AppCompatActivity {
iiiiimport android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

    public class ListadoMascotasActivity extends AppCompatActivity {

        private MascotaDataSource dataSource;
        private ArrayAdapter<String> adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_listado_mascotas);

            // Inicializar la fuente de datos
            dataSource = new MascotaDataSource(this);
            dataSource.open();

            // Referenciar el ListView en el layout
            ListView listViewMascotas = findViewById(R.id.listViewMascotas);

            // Configurar el adaptador para el ListView
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            listViewMascotas.setAdapter(adapter);

            // Obtener y mostrar todas las mascotas
            obtenerYMostrarMascotas();
        }

        private void obtenerYMostrarMascotas() {
            // Limpiar el adaptador antes de agregar las mascotas
            adapter.clear();

            // Obtener todas las mascotas de la base de datos
            Cursor cursor = dataSource.getAllMascotas();

            // Agregar las mascotas al adaptador
            if (cursor.moveToFirst()) {
                do {
                    String nombre = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE));
                    String raza = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RAZA));

                    // Agregar la mascota al adaptador
                    adapter.add("Nombre: " + nombre + ", Raza: " + raza);

                } while (cursor.moveToNext());
            }

            // Notificar al adaptador que los datos han cambiado
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            // Cerrar la conexión a la base de datos al salir de la actividad
            dataSource.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_veterinarias);
    }
