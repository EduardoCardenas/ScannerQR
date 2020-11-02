package com.example.scannerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Bienvenido extends AppCompatActivity {


    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        Auth = FirebaseAuth.getInstance();
    }

    /**
     * Metodo para abrir la seccion informacion.
     * @param view Elemento de la GUI que llama al evento.
     */
    public void IrInformacion(View view){
        Intent intent = new Intent(this, InformacionProducto.class);
        startActivity(intent);
    }


    /**
     * Metodo para abrir la seccion modificar.
     * @param view Elemento de la GUI que llama al evento.
     */
    public void IrModificar(View view){
        Intent intent = new Intent(this, ModificarProducto.class);
        startActivity(intent);

    }


    public void IrRegistrar(View view){
        Intent intent = new Intent(this, RegistrarProducto.class);
        startActivity(intent);
    }



    public void IrInicioSesion(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuoverflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        final int home = R.id.home;
        final int buscar = R.id.buscar;
        final int registrar = R.id.registrar;
        final int salir = R.id.salir;

        switch(item.getItemId()){
            case home:
                break;
            case buscar:
                break;
            case registrar:
                break;
            case salir:
                Auth.signOut();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}