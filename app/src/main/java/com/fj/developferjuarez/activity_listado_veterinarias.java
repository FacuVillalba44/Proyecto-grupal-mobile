package com.fj.developferjuarez;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ListadoVeterinariasActivity extends AppCompatActivity {

    private VeterinariaDataSource veterinariaDataSource;
    private VeterinariaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_veterinarias);

        // Inicializar la fuente de datos
        veterinariaDataSource = new VeterinariaDataSource(this);
        veterinariaDataSource.open();

        // Referenciar el ListView en el layout
        ListView listViewVeterinarias = findViewById(R.id.listViewVeterinarias);

        // Obtener y mostrar todas las veterinarias
        List<Veterinaria> veterinarias = veterinariaDataSource.getAllVeterinarias();
        adapter = new VeterinariaAdapter(this, R.layout.item_veterinaria, veterinarias);
        listViewVeterinarias.setAdapter(adapter);

        // Configurar el clic en un elemento de la lista
        listViewVeterinarias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abrirActivityVeterinaria(veterinarias.get(position));
            }
        });

        // Configurar el botón para volver al menú principal
        findViewById(R.id.btnVolverMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAMenu();
            }
        });
    }

    private void abrirActivityVeterinaria(Veterinaria veterinaria) {
        Intent intent = new Intent(this, ActivityVeterinaria.class);
        intent.putExtra("nombre", veterinaria.getNombre());
        intent.putExtra("direccion", veterinaria.getDireccion());
        startActivity(intent);
    }

    private void volverAMenu() {
        Intent intent = new Intent(this, ActivityMenu.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar la conexión a la base de datos al salir de la actividad
        veterinariaDataSource.close();
    }
}
