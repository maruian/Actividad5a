package com.example.a2dam.actividad5a;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrarUsuario extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth firebaseAuth;

    EditText usuario, correo, nombre, apellidos, direccion, password, passwordRepe;
    Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_usuario);

        usuario = findViewById(R.id.etNombreUsuario);
        correo = findViewById(R.id.etCorreoElectronico);
        nombre = findViewById(R.id.etNombre);
        apellidos = findViewById(R.id.etApellidos);
        direccion = findViewById(R.id.etDireccion);
        password = findViewById(R.id.etPassword);
        passwordRepe = findViewById(R.id.etRepetirPassword);

        registrar = findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if ((!TextUtils.isEmpty(password.getText().toString()))&&(!TextUtils.isEmpty(passwordRepe.getText().toString()))){
            if (password.getText().toString().equals(passwordRepe.getText().toString())){
                if ((!TextUtils.isEmpty(usuario.getText()))&&
                        (!TextUtils.isEmpty(correo.getText()))&&
                        (!TextUtils.isEmpty(nombre.getText()))&&
                        (!TextUtils.isEmpty(apellidos.getText()))&&
                        (!TextUtils.isEmpty(direccion.getText()))){
                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.createUserWithEmailAndPassword(correo.getText().toString(),password.getText().toString()).
                                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                             if (task.isSuccessful()){
                                                 Toast.makeText(getApplicationContext(),"Registro completo",Toast.LENGTH_SHORT).show();
                                                 finish();
                                             } else {
                                                 Toast.makeText(getApplicationContext(),"Error en el registro: "+task.getException(),Toast.LENGTH_SHORT).show();
                                             }
                                    }
                                });
                } else {
                    Toast.makeText(this,"Faltan datos",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,"Las contraseñas deben coincidir",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Debes introducir dos veces la contraseña",Toast.LENGTH_SHORT).show();
        }

    }
}
