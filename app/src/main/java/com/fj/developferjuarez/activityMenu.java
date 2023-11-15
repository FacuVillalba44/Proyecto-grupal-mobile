package com.fj.developferjuarez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import at.favre.lib.crypto.bcrypt.BCrypt;

// ...
import at.favre.lib.crypto.bcrypt.BCrypt;

// ...
import at.favre.lib.crypto.bcrypt.BCrypt;

// ...

public class UsuarioDataSource {

    // ...

    public long crearUsuario(String nombre, String contrasena) {
        // Generar una nueva sal
        String salt = BCrypt.withDefaults().generateSalt(10);

        // Hash y sal de la contraseña
        String contrasenaHash = BCrypt.withDefaults().hashToString(10, (nombre + contrasena).toCharArray());

        // Guardar el usuario en la base de datos
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOMBRE, nombre);
        values.put(DatabaseHelper.COLUMN_CONTRASENA, contrasenaHash);
        values.put(DatabaseHelper.COLUMN_SAL, salt);

        return database.insert(DatabaseHelper.TABLE_USUARIOS, null, values);
    }

    public Usuario obtenerUsuarioPorNombre(String nombre) {
        // Obtener el usuario desde la base de datos
        Cursor cursor = database.query(DatabaseHelper.TABLE_USUARIOS,
                allColumns, DatabaseHelper.COLUMN_NOMBRE + " = ?", new String[]{nombre},
                null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    return cursorToUsuario(cursor);
                }
            } finally {
                cursor.close();
            }
        }

        return null;
    }

    private Usuario cursorToUsuario(Cursor cursor) {
        // Convertir el cursor a un objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
        usuario.setNombre(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE)));
        usuario.setContrasena(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTRASENA)));

        // La sal se puede recuperar de la base de datos si es necesario

        return usuario;
    }

    // ...
}
