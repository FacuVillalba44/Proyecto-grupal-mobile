package com.pteplus.petplus.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbMascota extends BaseDeDatos{
    Context context;
    public DbMascota(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public  long agregarMascota(String nombre, String fecha_nacimiento, String especie, String raza, String sexo){
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

            id = db.insert(TABLE_MASCOTA, null, values);
        }
        catch (Exception ex){
            ex.toString();
        }

        return id;
    }

}
