package com.fj.developferjuarez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsuariosSQLiteHelper veterinaria =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = veterinaria.getWritableDatabase();
        if(db!=null) {
            for(int i=1; i<5; i++) {
                String nombre = "Usuario" + i;
                db.execSQL("INSERT INTO veterinaria (codigo, nombre, direccion) " + "VALUES (" + i + ", '"+ nombre +"', '"+ direccion + "')");
            }
            db.close();
        }
    }
}