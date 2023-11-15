package com.fj.developferjuarez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import at.favre.lib.crypto.bcrypt.BCrypt;

// ...

public class LoginActivity extends AppCompatActivity {

    // ...

    private void iniciarSesion() {
        String nombreUsuario = etNombreUsuario.getText().toString();
        String contrasena = etContrasena.getText().toString();

        // Validar que se ingresen datos antes de iniciar sesión
        if (!nombreUsuario.isEmpty() && !contrasena.isEmpty()) {
            // Obtener la información del usuario desde la base de datos
            Usuario usuario = usuarioDataSource.obtenerUsuarioPorNombre(nombreUsuario);

            if (usuario != null) {
                // Verificar la contraseña utilizando la función de hash y sal
                if (verificarContrasena(contrasena, usuario.getContrasena())) {
                    // Iniciar sesión exitosa
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                    // Aquí puedes abrir la actividad principal de tu aplicación
                    // por ejemplo, ActivityMenu.class
                    Intent intent = new Intent(this, ActivityMenu.class);
                    startActivity(intent);
                    finish();  // Cierra la actividad de inicio de sesión
                } else {
                    // Credenciales incorrectas
                    Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Usuario no encontrado
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Mostrar un mensaje indicando que se deben ingresar ambos campos
            Toast.makeText(this, "Por favor, ingrese nombre de usuario y contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verificarContrasena(String contrasena, String contrasenaAlmacenada) {
        // Utilizar la biblioteca BCrypt para verificar la contraseña
        BCrypt.Result result = BCrypt.verifyer().verify(contrasena.toCharArray(), contrasenaAlmacenada);

        return result.verified;
    }

    // ...
}

public class activityMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
}