package com.pteplus.petplus.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.pteplus.petplus.entidades.Mascotas;

import java.util.ArrayList;

public class DbMascota extends BaseDeDatos{
    Context context;
    BaseDeDatos baseDeDatos;
    DbUsuario dbUsuario;

    DbUsuario db;
    Mascotas mascotas;
    public DbMascota(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public  long crearMascota(String nombre, String fecha_nacimiento, String especie, String raza, String sexo, Integer id_usuario){
        long id = 0;

        try {
            DbMascota dbMascota = new DbMascota(context);
            SQLiteDatabase db = dbMascota.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(BaseDeDatos.COLUMN_NOMBRE_MASCOTA, nombre);
            values.put(BaseDeDatos.COLUMN_FECHA_NACIMIENTO, fecha_nacimiento);
            values.put(BaseDeDatos.COLUMN_ESPECIE, especie);
            values.put(BaseDeDatos.COLUMN_RAZA, raza);
            values.put(BaseDeDatos.COLUMN_SEXO, sexo);
            values.put(BaseDeDatos.COLUMN_ID_USUARIO, id_usuario);

            id = db.insert(TABLE_MASCOTA, null, values);
        }
        catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Mascotas> mostrarMascotas(){
        DbMascota dbMascota = new DbMascota(context);
        SQLiteDatabase db = dbMascota.getWritableDatabase();

        BaseDeDatos basededatos= new BaseDeDatos(context);
        SQLiteDatabase bdatos = basededatos.getWritableDatabase();



        ArrayList<Mascotas> listaMascotas = new ArrayList<>();
       /* String[] columns = {COLUMN_ID_MASCOTA, COLUMN_NOMBRE_MASCOTA, COLUMN_FECHA_NACIMIENTO, COLUMN_ESPECIE, COLUMN_RAZA, COLUMN_SEXO};
        String selection = COLUMN_ID_USUARIO + "=?";
        String[] selectionArgs = {String.valueOf(idUsuario)};

        Cursor cursor = db.query(TABLE_MASCOTA, columns, selection, selectionArgs, null, null, null);*/

        Mascotas mascotas = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_MASCOTA, null);


        if (cursor.moveToFirst()) {
            do {
                mascotas= new Mascotas();
                mascotas.setNombre(cursor.getString(1));
                mascotas.setFecha_nacimiento(cursor.getString(2));
                mascotas.setEspecie(cursor.getString(3));
                mascotas.setRaza(cursor.getString(4));
                mascotas.setSexo(cursor.getString(6));


               /* //int idMascota = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MASCOTA));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_MASCOTA));
                String fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_NACIMIENTO));
                String especie = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ESPECIE));
                String raza = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RAZA));
                String sexo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SEXO));

                // Crear objeto Mascota y añadirlo a la lista
                Mascotas mascota = new Mascotas();
                mascota.setNombre(nombre);
                mascota.setFecha_nacimiento(fechaNacimiento);
                mascota.setEspecie(especie);
                mascota.setRaza(raza);
                mascota.setSexo(sexo);*/

                Log.d("VALOR_ESPECIE", "Valor de Especie: " + cursor.getString(3));

                listaMascotas.add(mascotas);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaMascotas;
    }

}
