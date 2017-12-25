package com.example.a2dam.actividad5a;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a2dam.actividad5a.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements Opciones.OnFragmentInteractionListener, AltaUsuario.OnFragmentInteractionListener,
MostrarUsuarios.OnFragmentInteractionListener{


    FragmentManager fm;

    public static DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        bbdd = FirebaseDatabase.getInstance().getReference("Usuarios");
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
