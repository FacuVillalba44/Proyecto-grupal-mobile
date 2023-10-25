package com.pteplus.petplus;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;


import androidx.appcompat.app.AppCompatActivity;

import com.pteplus.petplus.bd.BaseDeDatos;
import com.pteplus.petplus.bd.DbMascota;
import com.pteplus.petplus.bd.DbUsuario;

public class AgregarMascotaActivity extends AppCompatActivity {
    private Button btnAgregarMascota, btnCancelar;

    private EditText etNombreMascota, etRaza, etEspecie, etSexo;
    private EditText etFechaNacimiento;


    DbMascota dbMascota;
    DbUsuario dbUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);

        btnAgregarMascota = findViewById(R.id.btnEditarMascota);
        btnCancelar = findViewById(R.id.btnEliminarMascota);
        etNombreMascota = findViewById(R.id.etNombreMascota);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etRaza = findViewById(R.id.etRaza);
        etEspecie = findViewById(R.id.etEspecie);
        etSexo = findViewById(R.id.etSexo);


        dbMascota = new DbMascota(this);

        dbUsuario = new DbUsuario(this);



        etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        btnAgregarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombreMascota.getText().toString();
                String fecha_nacimiento = etFechaNacimiento.getText().toString();
                String especie = etEspecie.getText().toString();
                String raza = etRaza.getText().toString();
                String sexo = etSexo.getText().toString();
                int idUsuario = getIntent().getIntExtra(BaseDeDatos.COLUMN_ID_USUARIO, -1);

                long agregarMascota = dbMascota.crearMascota(nombre, fecha_nacimiento, especie, raza, sexo, idUsuario);

                if(agregarMascota>0){
                    Toast.makeText(AgregarMascotaActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AgregarMascotaActivity.this, TusMascotasActivity.class);
                    intent.putExtra(BaseDeDatos.COLUMN_ID_USUARIO, idUsuario);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AgregarMascotaActivity.this, "Registro Fallido",Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarMascotaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            String fecha = dayOfMonth + "-" + (month + 1) + "-" + year;
            if (getActivity() instanceof AgregarMascotaActivity) {
                ((AgregarMascotaActivity) getActivity()).setFechaNacimiento(fecha);
            }
        }
    }

    private void setFechaNacimiento(String fecha_seleccionada) {
        etFechaNacimiento.setText(fecha_seleccionada);
    }
}