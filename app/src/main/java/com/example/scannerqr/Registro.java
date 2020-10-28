package com.example.scannerqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmpasswordEditText;

    //nos da la infoacionde usuario (correro y contraseña)
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usernameEditText = (EditText) findViewById(R.id.correo);
        passwordEditText = (EditText) findViewById(R.id.contrasena);
        confirmpasswordEditText = (EditText) findViewById(R.id.confirmar_contrasena);

        //"escucha" eventos de inico de sesion (avisa cuando inicio/cerro sesión)
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(Registro.this, "Verifique su cuenta", Toast.LENGTH_SHORT).show();
                    if (!user.isEmailVerified()){
                        Toast.makeText(Registro.this, "Verifique su correo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };


    }//fin de OnCreate


    private void IrBienvenido() {
        Intent intent = new Intent(this,Bienvenido.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }


    //Cuando oprimen el boton de registro
    public void signUp(View view){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmapassword = confirmpasswordEditText.getText().toString();

        if (!username.isEmpty() && !password.isEmpty()){

            if(password.length()>=6){
                if(password.length() == confirmapassword.length()){
                    firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Registro.this, "Error en el registro de usuario", Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                user.sendEmailVerification();
                                if (!user.isEmailVerified()) {
                                    Toast.makeText(Registro.this, "Verifique su correo", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }else{
                    Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "La contraseña debe ser mayor a 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Llene los campos de información", Toast.LENGTH_SHORT).show();
        }
    }




}