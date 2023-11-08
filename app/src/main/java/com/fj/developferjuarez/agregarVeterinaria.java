package com.fj.developferjuarez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class agregarVeterinaria extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "veterinaria.BD";
    private static final String VERSION_BD = 1;
    private static final TABLA_VETERINARIAS="CREATE TABLE VETERINARIAS(CODIGO TEXT PRIMARY KEY, NOMBRE TEXT, DIRECCION TEXT)";

    public agregarVeterinaria(Context context) {
        super(context, NOMBRE_BD, factory, VERSION_BD);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_VETERINARIAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXITS "+TABLA_VETERNARIAS);
        SQLiteDatabase.execSQL(TABLA_VETERINARIAS)
    }
    public void agregar agregarVeterinaria(String codigo, String nombre, String direccion);
    sqliteDatabase bd=getWritableDatabase();
    if (bd!=null){
        bd.execSQL("INSERT INTO NOMBRE VALUES ('"+CODIGO+"','"+NOMBRE+"','"+DIRECCION+"')");
        bd.close();
    }
}
