package com.example.a2dam.actividad5a;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a2dam.actividad5a.model.Usuario;


import java.util.ArrayList;

/**
 * Created by admin on 24/12/2017.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderAdaptador> {


    private ArrayList<Usuario>listadoUsuarios;

    public Adaptador(ArrayList<Usuario> listadoUsuarios){
       this.listadoUsuarios=listadoUsuarios;
    }

    @Override
    public ViewHolderAdaptador onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);
        return new ViewHolderAdaptador(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAdaptador holder, int position) {
        holder.asignarDatos(listadoUsuarios.get(position));
    }

    @Override
    public int getItemCount() {
        return listadoUsuarios.size();
    }

    public class ViewHolderAdaptador extends RecyclerView.ViewHolder {
        TextView text_usuario, text_correo, text_nombre, text_apellidos, text_direccion;

        public ViewHolderAdaptador(View itemView) {
            super(itemView);
            text_usuario = (TextView)itemView.findViewById(R.id.usuario);
            text_correo = (TextView)itemView.findViewById(R.id.correo);
            text_nombre = (TextView)itemView.findViewById(R.id.nombre);
            text_apellidos = (TextView)itemView.findViewById(R.id.apellidos);
            text_direccion = (TextView)itemView.findViewById(R.id.direccion);
        }

        public void asignarDatos(Usuario usuario) {
            text_usuario.setText(usuario.getUsuario());
            text_correo.setText(usuario.getCorreo());
            text_nombre.setText(usuario.getNombre());
            text_apellidos.setText(usuario.getApellidos());
            text_direccion.setText(usuario.getDireccion());
        }
    }
}
