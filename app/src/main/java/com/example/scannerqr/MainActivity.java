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

//VIDEO TUTORIAL CON EL QUE SE HIZO EL CODIGO www.youtube.com/watch?v=lPxE3D7Zf_s&list=PLMyztSzaoF1ZF7ulh9b6gjLjpEi5V4amV&index=10&ab_channel=aldominium

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    //nos da la infoacionde usuario (correro y contraseña)
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = (EditText) findViewById(R.id.correo);
        passwordEditText = (EditText) findViewById(R.id.contrasena);

        //"escucha" eventos de inico de sesion (avisa cuando inicio/cerro sesión)
        firebaseAuth = FirebaseAuth.getInstance();

        //AGREGADO EL 14/Oct/2020 A LAS 6:58 PM, solo el if(usernameEditText != null && passwordEditText != null)
        if(usernameEditText != null && passwordEditText != null){
            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    //Se puede meter en el metodo login para saber si ya esta verificado cuando se pulsa el boton
                    //Checa si el usuario esta logeado
                    if (user != null) {
                        if (!user.isEmailVerified()){
                            Toast.makeText(MainActivity.this, "Email no verificado", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            IrBienvenido();
                        }
                    }
                }
            };
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
        if (firebaseAuth.getCurrentUser() != null){
            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                IrBienvenido();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }


    //Metodo LOGIN si hubo error
    public void login(View view){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        //variable que ayuda a la autenticacion firebaseAuth
        //Liseert indica cuando estan iniciando sesión
        if (!username.isEmpty() && !password.isEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                    else{
                        if (firebaseAuth.getCurrentUser().isEmailVerified()){
                            IrBienvenido();
                        }
                    }
                }
            });
        }
        else{
            Toast.makeText(this, "Ingrese su usuario y contraseña", Toast.LENGTH_SHORT).show();
        }

    }


    //METODO PARA EL BOTON REGISTRO (que me mande a la pantalla registro)
    //El metodo se llama IrREGISTRO
    public void IrRegistro(View view){
        //se crea objeto Intent para hacer que los botones funciones
        //los parametros se pone (la activity de donde se parte "this", a la que se quiere ir "Registro.class")
        Intent boton_registro = new Intent(this, Registro.class);
        //vamos a indicar el mMETODO starActivity, en los parametros se pone el nombre del OBJETO intent "boton_registro"
        startActivity(boton_registro);

        //NO olvidar ir al XML y Activar este metodo en el boton (en atributos OnClik
    }//FIN DE METODO


    private void IrBienvenido(){
        //se crea objeto Intent para hacer que los botones funciones
        //los parametros se pone (la activity de donde se parte "this", a la que se quiere ir "Registro.class")
        Intent intent = new Intent(this, Bienvenido.class);
        //vamos a indicar el mMETODO starActivity, en los parametros se pone el nombre del OBJETO intent "boton_registro"
        startActivity(intent);
        finish();

        //NO olvidar ir al XML y Activar este metodo en el boton (en atributos OnClik
    }//FIN DE METODO

}