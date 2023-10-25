package com.pteplus.petplus.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pteplus.petplus.EditarMascotasActivity;
import com.pteplus.petplus.R;
import com.pteplus.petplus.VerMascotaActivity;
import com.pteplus.petplus.entidades.Mascotas;

import java.util.ArrayList;

public class ListaMascotasAdapter extends RecyclerView.Adapter<ListaMascotasAdapter.ContactoViewHolder> {
    ArrayList<Mascotas> listaMascotas;

    public ListaMascotasAdapter(ArrayList<Mascotas> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mascotas, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nombreMascota.setText(listaMascotas.get(position).getNombre());
        holder.fechaNacimientoMascota.setText(listaMascotas.get(position).getFecha_nacimiento());
        holder.especieMascota.setText(listaMascotas.get(position).getEspecie());
        holder.razaMascota.setText(listaMascotas.get(position).getRaza());
        String sexoMascota = listaMascotas.get(position).getSexo();


        String textoSexo;
        if (sexoMascota.equals("1")) {
            textoSexo = "Macho";
        } else if (sexoMascota.equals("2")) {
            textoSexo = "Hembra";
        } else {

            textoSexo = "Desconocido";
        }
        holder.sexoMascota.setText(textoSexo);


    }

    @Override
    public int getItemCount() {

        return listaMascotas.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreMascota, fechaNacimientoMascota, especieMascota, razaMascota, sexoMascota;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreMascota = itemView.findViewById(R.id.nombreMascota);
            fechaNacimientoMascota = itemView.findViewById(R.id.fechaNacimientoMascota);
            especieMascota = itemView.findViewById(R.id.especieMascota);
            razaMascota = itemView.findViewById(R.id.razaMascota);
            sexoMascota = itemView.findViewById(R.id.sexoMascota);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, VerMascotaActivity.class);
                    intent.putExtra("id_mascota", listaMascotas.get(getAdapterPosition()).getId_mascota());
                    context.startActivity(intent);
                }
            });
        }
    }
}
