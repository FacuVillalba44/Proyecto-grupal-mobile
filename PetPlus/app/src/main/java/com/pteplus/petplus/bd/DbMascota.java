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

                listaMascotas.add(mascotas);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaMascotas;
    }

    public Mascotas verMascota(int id_mascota){
        DbMascota dbMascota = new DbMascota(context);
        SQLiteDatabase db = dbMascota.getWritableDatabase();

        BaseDeDatos basededatos= new BaseDeDatos(context);
        SQLiteDatabase bbdd = basededatos.getWritableDatabase();

        Mascotas mascotas = null;
        Cursor cursor;

        cursor = bbdd.rawQuery("SELECT * FROM " + TABLE_MASCOTA + " WHERE id_mascota = " + id_mascota + " LIMIT 1" , null);


        if (cursor.moveToFirst()) {

            mascotas= new Mascotas();
            mascotas.setNombre(cursor.getString(1));
            mascotas.setFecha_nacimiento(cursor.getString(2));
            mascotas.setEspecie(cursor.getString(3));
            mascotas.setRaza(cursor.getString(4));
            mascotas.setSexo(cursor.getString(6));
        }

        cursor.close();
        return mascotas;
    }

    public  long editarMascota(int id_mascota, String nombre, String fecha_nacimiento, String especie, String raza, String sexo, Integer id_usuario){
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

    /*public boolean editarMascota(int id, String nombre, String fecha_nac, String especie, String raza, String sexo) {
        SQLiteDatabase db = null;
        boolean exito = false;

        try {
            DbMascota dbMascota = new DbMascota(context);
            db = dbMascota.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(DbMascota.COLUMN_NOMBRE_MASCOTA, nombre);
            valores.put(DbMascota.COLUMN_FECHA_NACIMIENTO, fecha_nac);
            valores.put(DbMascota.COLUMN_ESPECIE, especie);
            valores.put(DbMascota.COLUMN_RAZA, raza);
            valores.put(DbMascota.COLUMN_SEXO, sexo);

            int filasAfectadas = db.update(DbMascota.TABLE_MASCOTA, valores, DbMascota.COLUMN_ID_MASCOTA + " = ?", new String[]{String.valueOf(id)});

            if (filasAfectadas > 0) {
                exito = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return exito;
    }*/

}
