package com.fj.developferjuarez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android content.context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Usuarios (codigo INTEGER, nombre TEXT, direccion TEXT");
    public UsuariosSQLiteHelper (Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, direccion, factory, version)
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(sqlCreate);
    }
}
