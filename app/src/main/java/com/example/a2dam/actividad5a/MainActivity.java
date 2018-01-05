package com.example.a2dam.actividad5a;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Opciones.OnFragmentInteractionListener, AltaProducto.OnFragmentInteractionListener,
MostrarUsuarios.OnFragmentInteractionListener, EditarUsuari.OnFragmentInteractionListener, AltaUsuario.OnFragmentInteractionListener{

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        String usuario = getIntent().getStringExtra("USUARIO");
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
