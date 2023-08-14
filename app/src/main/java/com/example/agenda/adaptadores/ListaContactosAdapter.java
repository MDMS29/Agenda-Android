package com.example.agenda.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.MainActivity;
import com.example.agenda.R;
import com.example.agenda.VerActivity;
import com.example.agenda.entidades.Contactos;

import java.util.ArrayList;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {
    ArrayList<Contactos> listaContactos;

    public ListaContactosAdapter(ArrayList<Contactos> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.vwNombre.setText(listaContactos.get(position).getNombre());
        holder.vwTelefono.setText(listaContactos.get(position).getTelefono());
        holder.vwCorreo.setText(listaContactos.get(position).getCorreo());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView vwNombre, vwTelefono, vwCorreo;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            vwNombre = itemView.findViewById(R.id.vwNombre);
            vwTelefono = itemView.findViewById(R.id.vwTelefono);
            vwCorreo = itemView.findViewById(R.id.vwCorreo);

            itemView.setOnClickListener(v -> {
                Context context = vwNombre.getContext();
                Intent intent = new Intent(context, VerActivity.class);
                intent.putExtra("ID", listaContactos.get(getAdapterPosition()).getId());
                context.startActivity(intent);
            });
        }
    }
}
