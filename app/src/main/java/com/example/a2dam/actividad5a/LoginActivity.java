package com.example.a2dam.actividad5a;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2dam.actividad5a.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    public static FirebaseAuth firebaseAuth;
    Button entrar, registrar;
    EditText usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_login);

        usuario = findViewById(R.id.etUsuario);
        password = findViewById(R.id.etPassword);

        entrar = findViewById(R.id.btnEntrar);
        entrar.setOnClickListener(this);

        registrar = findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final Intent explicit_intent;
        switch (view.getId()){
            case R.id.btnEntrar:
                explicit_intent = new Intent(this, MainActivity.class);
                if (!TextUtils.isEmpty(usuario.getText())&&
                        (!TextUtils.isEmpty(password.getText()))){
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(usuario.getText().toString(),password.getText().toString()).
                            addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        //Toast.makeText(getApplicationContext(), "Signin successful " + user.getUid(), Toast.LENGTH_SHORT).show();
                                        explicit_intent.putExtra("USUARIO", usuario.getText().toString());
                                        startActivity(explicit_intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Sigin failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(this,"Debes introducir un nombre y una contraseña",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRegistrar:
                explicit_intent = new Intent(this,RegistrarUsuario.class);
                startActivity(explicit_intent);
                break;
            default:
                break;
        }

    }
}
