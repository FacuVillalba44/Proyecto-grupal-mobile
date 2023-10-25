package com.pteplus.petplus;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.pteplus.petplus.bd.DbMascota;
import com.pteplus.petplus.entidades.Mascotas;

import java.util.Calendar;

public class EditarMascotasActivity extends AppCompatActivity {
    EditText etNombreMascota, etFechaNacimiento, etEspecie, etRaza;
    Spinner spinner2;
    Mascotas mascota;
    Button btnGuardar, btnDelete;
    boolean exito;
    int id_mascota = 0;

    String[] opciones = {"Macho", "Hembra"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mascotas);

        btnDelete = findViewById(R.id.btnEliminar);
        btnGuardar = findViewById(R.id.btnGuardarMascota);

        etNombreMascota = findViewById(R.id.etNombreMascota);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etEspecie = findViewById(R.id.etEspecie);
        etRaza = findViewById(R.id.etRaza);
        spinner2 = findViewById(R.id.spinner2);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        spinner2.setAdapter(adapter);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id_mascota = Integer.parseInt(null);
            }else {
                id_mascota = extras.getInt("id_mascota");
            }
        }else{
            id_mascota = (int) savedInstanceState.getSerializable("id_mascota");
        }

        DbMascota dbMascota = new DbMascota(EditarMascotasActivity.this);
        mascota = dbMascota.verMascota(id_mascota);

        if (mascota != null) {
            etNombreMascota.setText(mascota.getNombre());
            etFechaNacimiento.setText(mascota.getFecha_nacimiento());
            etEspecie.setText(mascota.getEspecie());
            etRaza.setText(mascota.getRaza());

        }

        etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }

            private void showDatePickerDialog() {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para editar
                String sexo = spinner2.getSelectedItem().toString();


                /*if(!etNombreMascota.getText().toString().equals("") && !etFechaNacimiento.getText().toString().equals("") && !etEspecie.getText().toString().equals("") && !etRaza.getText().toString().equals("") && !sexo.equals("")) {
                    DbMascota dbMascota = new DbMascota(EditarMascotasActivity.this);
                    int id = mascota.getId_mascota();

                    if (mascota!= null){

                        String nombre = etNombreMascota.getText().toString();
                        String fecha_nac = etFechaNacimiento.getText().toString();
                        String especie = etEspecie.getText().toString();
                        String raza = etRaza.getText().toString();

                        exito = dbMascota.editarMascota( id, nombre, fecha_nac, especie, raza, sexo);

                        if(exito){
                            Toast.makeText(EditarMascotasActivity.this, "Registro modificado con exito", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(EditarMascotasActivity.this, "Error al modificar el registro", Toast.LENGTH_SHORT).show();
                        }
                    }

                }*/
            }
        });
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
            EditText etFechaNacimiento = getActivity().findViewById(R.id.etFechaNacimiento);
            etFechaNacimiento.setText(fecha);
        }
    }
}