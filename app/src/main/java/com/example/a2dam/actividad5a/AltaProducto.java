package com.example.a2dam.actividad5a;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a2dam.actividad5a.model.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AltaProducto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AltaProducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AltaProducto extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button guardar;
    private EditText text_nombre, text_descripcion, text_precio;
    private Spinner spCategoria;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AltaProducto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AltaProducto.
     */
    // TODO: Rename and change types and number of parameters
    public static AltaProducto newInstance(String param1, String param2) {
        AltaProducto fragment = new AltaProducto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.alta_producto, container, false);

        text_nombre = v.findViewById(R.id.etNombreProducto);
        text_descripcion = v.findViewById(R.id.etDescripcionProducto);
        spCategoria = v.findViewById(R.id.spCategoria);
        text_precio = v.findViewById(R.id.etPrecio);
        guardar = v.findViewById(R.id.guardar);

        guardar.setOnClickListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guardar:
                DatabaseReference databaseReferenceProductos = FirebaseDatabase.getInstance().getReference("Productos");
                DatabaseReference databaseReferenceProductosXUsuario = FirebaseDatabase.getInstance().getReference("ProductosXUsuario");
                DatabaseReference databaseReferenceProductosXCategoria = FirebaseDatabase.getInstance().getReference("ProductosXCategoria");

                String nombre = text_nombre.getText().toString();
                String descripcion = text_descripcion.getText().toString();
                String categoria = spCategoria.getSelectedItem().toString();
                String precio = text_precio.getText().toString();
                String uid = MainActivity.usuarioSesion.getUid();
                String usuario = MainActivity.usuarioSesion.getUsuario();

                if (!TextUtils.isEmpty(nombre) &&
                        !TextUtils.isEmpty(descripcion) &&
                        !TextUtils.isEmpty(categoria) &&
                        !TextUtils.isEmpty(precio)) {

                    String claveProducto = databaseReferenceProductos.push().getKey();

                    Producto p = new Producto(nombre, descripcion, categoria, precio, uid, usuario, claveProducto);

                    databaseReferenceProductos.child(claveProducto).setValue(p);

                    // modificacion
                    // databaseReferenceProductosXUsuario.child(MainActivity.usuarioSesion.getUid()).child(claveProducto).setValue(p);
                    databaseReferenceProductosXUsuario.child(MainActivity.usuarioSesion.getUsuario()).child(claveProducto).setValue(p);

                    databaseReferenceProductosXCategoria.child(p.getCategoria()).child(claveProducto).setValue(p);

                    Toast.makeText(getContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                    text_nombre.setText("");
                    text_descripcion.setText("");
                    text_precio.setText("");
                    spCategoria.setSelection(0);

                } else {
                    Toast.makeText(getContext(), "Debes introducir datos correctos", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
