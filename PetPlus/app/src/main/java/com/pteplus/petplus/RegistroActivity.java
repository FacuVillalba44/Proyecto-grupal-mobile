package com.pteplus.petplus;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.pteplus.petplus.bd.BaseDeDatos;
import com.pteplus.petplus.bd.DbUsuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellido, editTextEmail, editTextPassword1, editTextPassword2 ;
    private Button btnRegistro;
    private ImageView imageView;

    DbUsuario db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextPassword1 = findViewById(R.id.editTextPassword1);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        btnRegistro = findViewById(R.id.btnRegistro);
        imageView = findViewById(R.id.imageview);
        db= new DbUsuario(this);


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombre.getText().toString().trim();
                String apellido = editTextApellido.getText().toString();
                String email = editTextEmail.getText().toString();
                String password1 = editTextPassword1.getText().toString();
                String password2 = editTextPassword2.getText().toString();

                if ( nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password1.isEmpty() || password2.isEmpty()){
                    Toast.makeText(RegistroActivity.this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (password1.equals(password2)){
                        Boolean chekemail = db.checkEmail(email);

                        if (chekemail== false){
                            long crearUsuario = db.crearUsuario(nombre, apellido, email, password1);
                            limpiar();

                            if( crearUsuario>0){
                                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegistroActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegistroActivity.this, "El email ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void limpiar(){
        editTextEmail.setText("");
        editTextNombre.setText("");
        editTextApellido.setText("");
        editTextPassword1.setText("");
        editTextPassword2.setText("");

    }
}