package com.example.a2dam.actividad5a;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2dam.actividad5a.model.Producto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by admin on 06/01/2018.
 */

public class AdaptadorTodosProductos extends RecyclerView.Adapter<AdaptadorTodosProductos.ViewHolderAdaptador> {

    private ArrayList<Producto> listadoProductos;
    private FragmentManager fm;
    private String keyProducto;
    int lastPosition=-1;


    public AdaptadorTodosProductos(ArrayList<Producto> listadoProductos, FragmentManager fm) {
        this.listadoProductos = listadoProductos;
        this.fm = fm;
    }

    @Override
    public ViewHolderAdaptador onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_producto,parent,false);
        return new ViewHolderAdaptador(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAdaptador holder, int position) {
        Producto p = listadoProductos.get(position);
        holder.asignarDatos(p);
    }

    @Override
    public int getItemCount() {
        return listadoProductos.size();
    }


    public class ViewHolderAdaptador extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre, descripcion, categoria, precio, usuario, favorito;

        public ViewHolderAdaptador(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombre = (TextView)itemView.findViewById(R.id.nombreProducto);
            descripcion = (TextView)itemView.findViewById(R.id.descripcionProducto);
            categoria = (TextView)itemView.findViewById(R.id.categoriaProducto);
            precio = (TextView)itemView.findViewById(R.id.precioProducto);
            usuario = (TextView)itemView.findViewById(R.id.usuarioProducto);
            favorito = (TextView)itemView.findViewById(R.id.favorito);
        }

        public void asignarDatos(Producto p){
            nombre.setText(p.getNombre());
            descripcion.setText(p.getDescripcion());
            categoria.setText(p.getCategoria());
            precio.setText(p.getPrecio());
            usuario.setText(p.getUsuario());
            keyProducto = p.getKey();
        }

        public void onClick(View view) {
            Context context = view.getContext();
            int position = getAdapterPosition();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Favoritos");
            if (position == lastPosition) {
                favorito.setTextColor(Color.WHITE);
                databaseReference = FirebaseDatabase.getInstance().getReference(
                        "Favoritos/"+MainActivity.usuarioSesion.getUsuario()+keyProducto);
                databaseReference.removeValue();
            } else {
                favorito.setTextColor(Color.RED);
                databaseReference.child(MainActivity.usuarioSesion.getUsuario()+keyProducto).setValue(keyProducto);
                lastPosition = position;
            }

        }

    }
}
