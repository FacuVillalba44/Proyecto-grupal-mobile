package com.pteplus.petplus.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    Context context;
    int id_usuario;

    public ListaMascotasAdapter(ArrayList<Mascotas> listaMascotas, Context context, int id_usuario) {
        this.listaMascotas = listaMascotas;
        this.context = context;
        this.id_usuario = id_usuario;
    }
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mascotas, parent, false);
        return new ContactoViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Mascotas mascotas = listaMascotas.get(position);
        int mascotaId = listaMascotas.get(position).getId_mascota();
        Log.d("ID_MASCOTA_ADAPTER", "ID de la mascota en el adaptador: " + mascotas.getId_mascota());


        holder.nombreMascota.setText(listaMascotas.get(position).getNombre());
        holder.fechaNacimientoMascota.setText(listaMascotas.get(position).getFecha_nacimiento());
        holder.especieMascota.setText(listaMascotas.get(position).getEspecie());
        holder.razaMascota.setText(listaMascotas.get(position).getRaza());
        holder.sexoMascota.setText(listaMascotas.get(position).getSexo());
    }
    public void setListaMascotas(ArrayList<Mascotas> listaMascotas) {
        this.listaMascotas = listaMascotas;
        notifyDataSetChanged();
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
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Mascotas mascota = listaMascotas.get(position);

                        Intent intent = new Intent(context, VerMascotaActivity.class);
                        intent.putExtra("id_usuario", id_usuario);
                        intent.putExtra("id_mascota", mascota.getId_mascota());
                        intent.putExtra("nombre", mascota.getNombre());
                        intent.putExtra("fechaNacimiento", mascota.getFecha_nacimiento());
                        intent.putExtra("especie", mascota.getEspecie());
                        intent.putExtra("raza", mascota.getRaza());
                        intent.putExtra("sexo", mascota.getSexo());
                        context.startActivity(intent);

                        /*startActivityForResult(intent, 1000);*/

                    }
                }
            });
        }
    }
}
