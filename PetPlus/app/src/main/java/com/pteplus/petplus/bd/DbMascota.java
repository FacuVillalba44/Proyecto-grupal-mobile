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

    public long crearMascota(String nombre, String fecha_nacimiento, String especie, String raza, String sexo, Integer id_usuario) {
        long id = -1;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BaseDeDatos.COLUMN_NOMBRE_MASCOTA, nombre);
        values.put(BaseDeDatos.COLUMN_FECHA_NACIMIENTO, fecha_nacimiento);
        values.put(BaseDeDatos.COLUMN_ESPECIE, especie);
        values.put(BaseDeDatos.COLUMN_RAZA, raza);
        values.put(BaseDeDatos.COLUMN_SEXO, sexo);
        values.put(BaseDeDatos.COLUMN_ID_USUARIO, id_usuario);

        try {
            id = db.insert(TABLE_MASCOTA, null, values);
        } catch (Exception ex) {
            Log.e("DbMascota", "Error al crear mascota: " + ex.getMessage());
        } finally {
            db.close();
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
    public ArrayList<Mascotas> mostrarMascotas(int id_usuario) {
        ArrayList<Mascotas> listaMascotas = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MASCOTA + " WHERE " + BaseDeDatos.COLUMN_ID_USUARIO + " = ?", new String[]{String.valueOf(id_usuario)});

        if (cursor.moveToFirst()) {
            do {
                Mascotas mascotas = new Mascotas();
                mascotas.setId_mascota(cursor.getInt(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_ID_MASCOTA)));
                mascotas.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_NOMBRE_MASCOTA)));
                mascotas.setFecha_nacimiento(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_FECHA_NACIMIENTO)));
                mascotas.setEspecie(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_ESPECIE)));
                mascotas.setRaza(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_RAZA)));
                mascotas.setSexo(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_SEXO)));

                listaMascotas.add(mascotas);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaMascotas;
    }

    public Mascotas verMascota(int id_mascota){
        DbMascota dbMascota = new DbMascota(context);
        SQLiteDatabase db = dbMascota.getWritableDatabase();


        Mascotas mascotas = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_MASCOTA + " WHERE " + BaseDeDatos.COLUMN_ID_MASCOTA + " = " + id_mascota + " LIMIT 1", null);
            if (cursor.moveToFirst()) {

                mascotas = new Mascotas();
                mascotas.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_NOMBRE_MASCOTA)));
                mascotas.setFecha_nacimiento(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_FECHA_NACIMIENTO)));
                mascotas.setEspecie(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_ESPECIE)));
                mascotas.setRaza(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_RAZA)));
                mascotas.setSexo(cursor.getString(cursor.getColumnIndexOrThrow(BaseDeDatos.COLUMN_SEXO)));
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return mascotas;
    }


    public  boolean editarMascota(int id, String nombre, String fecha_nacimiento, String especie, String raza, String sexo){

        boolean exito = false;

        DbMascota dbMascota = new DbMascota(context);
        SQLiteDatabase db = dbMascota.getWritableDatabase();

        try{
            db.execSQL("UPDATE " + TABLE_MASCOTA + " SET " + BaseDeDatos.COLUMN_NOMBRE_MASCOTA + " = '" + nombre + "', " +
                    BaseDeDatos.COLUMN_FECHA_NACIMIENTO + " = '" + fecha_nacimiento + "', " +
                    BaseDeDatos.COLUMN_ESPECIE + " = '" + especie + "', " +
                    BaseDeDatos.COLUMN_RAZA + " = '" + raza + "', " +
                    BaseDeDatos.COLUMN_SEXO + " = '" + sexo + "' " +
                    " WHERE " + BaseDeDatos.COLUMN_ID_MASCOTA + " = " + id);

            exito = true;
        }catch (Exception ex){
            ex.toString();
            exito = false;
        }finally {
            db.close();
        }
        return exito;
    }

    public  boolean eliminarMascota(int id){

        boolean exito = false;

        DbMascota dbMascota = new DbMascota(context);
        SQLiteDatabase db = dbMascota.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM " + TABLE_MASCOTA  +
                    " WHERE " + BaseDeDatos.COLUMN_ID_MASCOTA + " = " + id);
            exito = true;
        }catch (Exception ex){
            ex.toString();
            exito = false;
        }finally {
            db.close();
        }
        return exito;
    }


}
