package com.pteplus.petplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.pteplus.petplus.adaptadores.ListaMascotasAdapter;
import com.pteplus.petplus.bd.BaseDeDatos;
import com.pteplus.petplus.bd.DbMascota;
import com.pteplus.petplus.entidades.Mascotas;
import com.pteplus.petplus.item_decorador.DivisorItem;

import java.util.ArrayList;

public class TusMascotasActivity extends AppCompatActivity {
    RecyclerView listaMascotas;
    ArrayList<Mascotas> listaArrayMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tus_mascotas);

        listaMascotas = findViewById(R.id.listaMascotas);
        listaMascotas.setLayoutManager(new LinearLayoutManager(this));

        int id_usuario = getIntent().getIntExtra(BaseDeDatos.COLUMN_ID_USUARIO, -1);

        int dividerColor = getResources().getColor(R.color.colorDivider);
        int dividerHeightInPixels = getResources().getDimensionPixelSize(R.dimen.divider_height);
        DivisorItem divisor = new DivisorItem(dividerColor, dividerHeightInPixels);
        listaMascotas.addItemDecoration(divisor);

        DbMascota dbMascota = new DbMascota(TusMascotasActivity.this);
        listaArrayMascotas = new ArrayList<>();

        ListaMascotasAdapter adapter = new ListaMascotasAdapter(dbMascota.verMascota(id_usuario));
        listaMascotas.setAdapter(adapter);


    }
}