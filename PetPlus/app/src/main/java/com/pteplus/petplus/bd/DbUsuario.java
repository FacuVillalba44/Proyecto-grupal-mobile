package com.pteplus.petplus.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
public class DbUsuario extends BaseDeDatos{
    Context context;

    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long crearUsuario (String nombre, String apellido, String email, String password){

        long id = 0;

        try{
            BaseDeDatos DataBase = new BaseDeDatos(context);
            SQLiteDatabase db = DataBase.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(BaseDeDatos.COLUMN_NOMBRE, nombre);
            values.put(BaseDeDatos.COLUMN_APELLIDO, apellido);
            values.put(BaseDeDatos.COLUMN_EMAIL, email);
            values.put(BaseDeDatos.COLUMN_PASSWORD, password);

            id= db.insert(TABLE_USUARIO, null, values);

        } catch (Exception exc){
            exc.toString();
        }

        return id;
    }

   /*public boolean verificarCredenciales(String email, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        String consulta = "SELECT * FROM " + TABLE_USUARIO + " WHERE " +
                BaseDeDatos.COLUMN_EMAIL + " = ? AND " + BaseDeDatos.COLUMN_PASSWORD + " = ?";

        Cursor cursor = db.rawQuery(consulta, new String []{email, password});
        boolean loginExitoso = cursor.getCount()>0;
        cursor.close();
        db.close();
        return loginExitoso;
    }*/

    /*public int obtenerIdUsuario(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = -1; // Valor predeterminado en caso de que no se encuentre el usuario

        String[] columns = {COLUMN_ID_USUARIO};
        String selection = COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USUARIO, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(COLUMN_ID_USUARIO);
            userId = cursor.getInt(columnIndex);
        }

        cursor.close();
        db.close();
        System.out.print(userId);
        return userId;
    }*/

    public boolean checkEmail (String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_USUARIO +" where "+ COLUMN_EMAIL + " =?", new String []{email});

        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USUARIO + " where " + COLUMN_EMAIL + " =? and " + COLUMN_PASSWORD + " =?", new String[]{email, password});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}
