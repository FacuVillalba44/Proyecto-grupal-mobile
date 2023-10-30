package com.pteplus.petplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.pteplus.petplus.adaptadores.ListaMascotasAdapter;
import com.pteplus.petplus.bd.BaseDeDatos;
import com.pteplus.petplus.bd.DbMascota;
import com.pteplus.petplus.entidades.Mascotas;
import com.pteplus.petplus.item_decorador.DivisorItem;

import java.util.ArrayList;

public class TusMascotasActivity extends AppCompatActivity {
    RecyclerView listaMascotas;
    ArrayList<Mascotas> listaArrayMascotas;
    DbMascota dbMascota;
    int id_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tus_mascotas);

        listaMascotas = findViewById(R.id.listaMascotas);
        listaMascotas.setLayoutManager(new LinearLayoutManager(this));


<<<<<<< HEAD
        int id_usuario = getIntent().getIntExtra("id_usuario", -1);
=======
        int dividerColor = getResources().getColor(R.color.colorDivider);
        int dividerHeightInPixels = getResources().getDimensionPixelSize(R.dimen.divider_height);
        DivisorItem divisor = new DivisorItem(dividerColor, dividerHeightInPixels);
        listaMascotas.addItemDecoration(divisor);

        DbMascota dbMascota = new DbMascota(TusMascotasActivity.this);
        listaArrayMascotas = new ArrayList<>();

        // Crear un ArrayList de objetos Mascotas a partir del objeto Mascotas devuelto por el método verMascota()
        listaArrayMascotas.add(dbMascota.verMascota(id_usuario));

        ListaMascotasAdapter adapter = new ListaMascotasAdapter(listaArrayMascotas);
        listaMascotas.setAdapter(adapter);
>>>>>>> bf6fbd951cca3f039d032632282573927cc32e6a



        if (id_usuario != -1) {
            int dividerColor = getResources().getColor(R.color.colorDivider);
            int dividerHeightInPixels = getResources().getDimensionPixelSize(R.dimen.divider_height);
            DivisorItem divisor = new DivisorItem(dividerColor, dividerHeightInPixels);
            listaMascotas.addItemDecoration(divisor);


            dbMascota = new DbMascota(TusMascotasActivity.this);


            listaArrayMascotas = dbMascota.mostrarMascotas(id_usuario);


            ListaMascotasAdapter adapter = new ListaMascotasAdapter(listaArrayMascotas, this, id_usuario);
            listaMascotas.setAdapter(adapter);


            adapter.notifyDataSetChanged();
        } else {

        }
    }
}