package com.example.a2dam.actividad5a;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.a2dam.actividad5a.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements Opciones.OnFragmentInteractionListener, AltaProducto.OnFragmentInteractionListener,
MostrarUsuarios.OnFragmentInteractionListener, EditarUsuari.OnFragmentInteractionListener, AltaUsuario.OnFragmentInteractionListener,
        MostrarProductos.OnFragmentInteractionListener, ModificarProducto.OnFragmentInteractionListener, MostrarTodosProductos.OnFragmentInteractionListener{


    public static Usuario usuarioSesion;
    DatabaseReference databaseReference;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        String usuario = getIntent().getStringExtra("USUARIO");
        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");
        Query q = databaseReference.orderByChild("correo").equalTo(usuario);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    usuarioSesion = (Usuario)datasnapshot.getValue(Usuario.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Toast.makeText(this,"Bienvenido "+usuario,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean estaFragmentDinamic() {
        Fragment f = fm.findFragmentById(R.id.fragment_dinamic);
        if (f == null) {
            return false;
        } else {
            return true;
        }
    }

}
