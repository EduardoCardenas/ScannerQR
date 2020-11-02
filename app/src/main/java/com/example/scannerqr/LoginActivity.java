package com.example.scannerqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText etCorreo;
    private EditText etPassword;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        if(userAlreadyLoggedIn()){
            irBienvenido();
        }

        setContentView(R.layout.activity_login);

        etCorreo = findViewById(R.id.etCorreoLogin);
        etPassword = findViewById(R.id.etPasswordLogin);

    }

    private boolean userAlreadyLoggedIn(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return user != null;
    }

    public void login(View view){
        hideKeyboard();
        String username = etCorreo.getText().toString();
        String password = etPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Ingrese su correo y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                        irBienvenido();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "No se ha verificado el correo registrado.", Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void IrRegistro(View view){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    private void irBienvenido(){
        Intent intent = new Intent(this, Bienvenido.class);
        startActivity(intent);
        finish();
    }

    private void hideKeyboard(){
        View view = findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}