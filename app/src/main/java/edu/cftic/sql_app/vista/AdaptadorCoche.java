package edu.cftic.sql_app.vista;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dto.Coche;

public class AdaptadorCoche  extends RecyclerView.Adapter<AdaptadorCoche.ViewHolderDatos> {


    private ArrayList<Coche> datos;

    public AdaptadorCoche(ArrayList<Coche> datos) {
        this.datos = datos;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celdacoche, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder._id.setText( ""+datos.get(position).getId());
        holder.mod.setText(datos.get(position).getModelo());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    // Clase Interna
    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView _id;
        TextView mod;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            _id = (TextView) itemView.findViewById(R.id.idcoche);
            mod = (TextView) itemView.findViewById(R.id.modelo);
        }

    }

}
